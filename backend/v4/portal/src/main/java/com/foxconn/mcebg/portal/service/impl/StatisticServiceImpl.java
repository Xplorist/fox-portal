package com.foxconn.mcebg.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.foxconn.mcebg.portal.mapper.base.StatisticMapper;
import com.foxconn.mcebg.portal.mapper.base.SystemInfoMapper;
import com.foxconn.mcebg.portal.mapper.follow.MatomoMapper;
import com.foxconn.mcebg.portal.model.MobileAppInfoDO;
import com.foxconn.mcebg.portal.model.SystemInfoDO;
import com.foxconn.mcebg.portal.model.dto.*;
import com.foxconn.mcebg.portal.model.query.ListActiveUserQuery;
import com.foxconn.mcebg.portal.model.query.MatomoMapperQuery;
import com.foxconn.mcebg.portal.model.query.StatisticDataQuery;
import com.foxconn.mcebg.portal.model.query.VipAcessNumQuery;
import com.foxconn.mcebg.portal.model.vo.*;
import com.foxconn.mcebg.portal.service.StatisticService;
import com.foxconn.mcebg.portal.util.DTOConverter;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticMapper mapper;
    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    MatomoMapper matomoMapper;

    // statistic【01】查询系统list
    @Override
    public ResultDTO listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 查询系统list
        List<SystemInfoDO> systemInfoDOS = systemInfoMapper.selectList(new QueryWrapper<SystemInfoDO>().lambda()
                .eq(SystemInfoDO::getValidFlag, 1)
                .ne(SystemInfoDO::getUrl, JdbcType.NULL)
                .orderByAsc(SystemInfoDO::getListOrder));

        List<SystemInfoDTO> list = new ArrayList<>();

        for (int i = 0; i < systemInfoDOS.size(); i++) {
            SystemInfoDO systemInfoDO = systemInfoDOS.get(i);
            SystemInfoDTO dto = new SystemInfoDTO();
            dto = (SystemInfoDTO) DTOConverter.parseDoToDTO(systemInfoDO, dto);
            list.add(dto);
        }

        return new ResultDTO("1", "statistic【01】查询系统list成功", list);
    }

    // deviceType参数检验
    public String deviceTypeCheck(String deviceType, String systemId) {
        // deviceType参数检验
        if (!"all".equals(deviceType) && !"pc".equals(deviceType) && !"mobile".equals(deviceType)) {
            throw new RuntimeException("设备类型只能为all或pc或mobile");
        }

        SystemInfoDO systemInfo = systemInfoMapper.selectOne(new QueryWrapper<SystemInfoDO>().lambda().eq(SystemInfoDO::getId, systemId).eq(SystemInfoDO::getValidFlag, 1));
        if (systemInfo == null) {
            throw new RuntimeException("系统id=【" + systemId + "】的系统不存在");
        }
        String systemUrl = systemInfo.getUrl();
        if (systemUrl == null || "".equals(systemUrl)) {
            throw new RuntimeException("系统id=【" + systemId + "】的系统URL为空");
        }

        return systemUrl;
    }

    // statistic【02】根据条件查询系统统计数据
    @Override
    public ResultDTO queryStatisticData(StatisticDataQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = param.getSystemId();
        String dateType = param.getDateType();
        String deviceType = param.getDeviceType();

        String startDate = "";
        String endDate = "";

        // dateType参数检验
        if (!"week".equals(dateType) && !"month".equals(dateType) && !"year".equals(dateType)) {
            int index = dateType.indexOf(",");
            if (index <= 0) {
                throw new RuntimeException("自定义日期范围必须以‘,’（英文逗号）分隔");
            }
            String[] strArray = dateType.split(",");
            for (int i = 0; i < strArray.length; i++) {
                String str = strArray[i].trim();
                Date strDate = new SimpleDateFormat("yyyy-MM-dd").parse(str);
                if (strDate == null) {
                    throw new RuntimeException("自定义日期范围日期只能为yyyy-MM-dd格式");
                }
                if (i == 0) {
                    startDate = str;
                } else {
                    endDate = str;
                }
            }
        }

        // deviceType参数检验
        String systemUrl = deviceTypeCheck(deviceType, systemId);

        //String nowDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        List<StatisticChartDataDTO> chartDataList = new ArrayList<>();

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long dayLong = 1000 * 60 * 60 * 24;

        MatomoMapperQuery matomoParam = new MatomoMapperQuery();
        matomoParam.setSystemUrl(systemUrl);
        matomoParam.setDeviceType(deviceType);

        if ("week".equals(dateType)) {
            // 近7天
            for (int i = 6; i >= 0; i--) {
                Date theDate = new Date(nowDate.getTime() - dayLong * i);
                String theDateStr = sdf.format(theDate);
                //addChartData(systemId, systemUrl, chartDataList, theDateStr);

                matomoParam.setTheDate(theDateStr);
                matomoParam.setDateType("theDate");
                // 添加图表数据
                addChartData(systemId, chartDataList, matomoParam);
            }
        } else if ("month".equals(dateType)) {
            // 近1月
            for (int i = 29; i >= 0; i--) {
                Date theDate = new Date(nowDate.getTime() - dayLong * i);
                String theDateStr = sdf.format(theDate);
                //addChartData(systemId, systemUrl, chartDataList, theDateStr);

                matomoParam.setTheDate(theDateStr);
                matomoParam.setDateType("theDate");
                // 添加图表数据
                addChartData(systemId, chartDataList, matomoParam);
            }
        } else if ("year".equals(dateType)) {
            // 近1年
            LocalDate localDate = LocalDate.now();
            for (int i = 11; i >= 0; i--) {
                LocalDate startLocal = localDate.minusMonths(i);

                int yearValue = startLocal.getYear();
                int monthValue = startLocal.getMonthValue();
                String monthStr = monthValue < 10 ? monthStr = "0" + monthValue : "" + monthValue;

                startLocal = LocalDate.parse(yearValue + "-" + monthStr + "-01", dtf);
                LocalDate endLocal = startLocal.plusMonths(1);

                String theDateStr = yearValue + "-" + monthStr;

                matomoParam.setTheDate(theDateStr);
                matomoParam.setDateType("timePeriod");
                matomoParam.setStartDate(dtf.format(startLocal));
                matomoParam.setEndDate(dtf.format(endLocal));

                // 添加图表数据
                addChartData(systemId, chartDataList, matomoParam);
            }
        } else {
            // 自定义时间范围（不能超过60天）
            LocalDate startDateLocal = LocalDate.parse(startDate, dtf);
            LocalDate endDateLocal = LocalDate.parse(endDate, dtf);

            boolean isBefore = endDateLocal.isBefore(startDateLocal.plusDays(61));
            if (!isBefore) {
                throw new RuntimeException("自定义时间范围不能超过60天");
            }

            while (startDateLocal.compareTo(endDateLocal) <= 0) {
                //addChartData(systemId, systemUrl, chartDataList, dtf.format(startDateLocal));
                matomoParam.setTheDate(dtf.format(startDateLocal));
                matomoParam.setDateType("theDate");
                // 添加图表数据
                addChartData(systemId, chartDataList, matomoParam);
                startDateLocal = startDateLocal.plusDays(1);
            }
        }

        QueryStatisticDataVO vo = new QueryStatisticDataVO();
        vo.setChartDataList(chartDataList);

        return new ResultDTO("1", "statistic【02】根据条件查询系统统计数据", vo);
    }

    // 添加图表数据
    public void addChartData(String systemId, List<StatisticChartDataDTO> chartDataList, MatomoMapperQuery param) {
        StatisticChartDataDTO dto = new StatisticChartDataDTO();

        if (param == null) {
            throw new RuntimeException("MatomoMapperQuery的实例param不能为空");
        }
        String systemUrl = param.getSystemUrl();
        if (systemUrl == null || "".equals(systemUrl)) {
            throw new RuntimeException("systemUrl不能为空");
        }
        String deviceType = param.getDeviceType();
        if (deviceType == null || "".equals(deviceType)) {
            throw new RuntimeException("deviceType不能为空");
        }
        String dateType = param.getDateType();
        if (dateType == null || "".equals(dateType)) {
            throw new RuntimeException("dateType不能为空");
        }
        String theDateStr = param.getTheDate();
        if (theDateStr == null || "".equals(theDateStr)) {
            throw new RuntimeException("MatomoMapperQuery参数的theDateStr不能为空");
        }
        if ("timePeriod".equals(dateType)) {
            String startDate = param.getStartDate();
            String endDate = param.getEndDate();
            if (startDate == null || "".equals(startDate) || endDate == null || "".equals(endDate)) {
                throw new RuntimeException("startDate和endDate都不能为空");
            }
        }

        Long userSum = 0L;
        Long activeUserSum = 0L;
        Long accessSum = 0L;
        Long deviceSum = 0L;
        Long activeDeviceSum = 0L;

        Set<String> userSet = new HashSet<>();
        Set<String> deviceSet = new HashSet<>();

        List<String> userList = new ArrayList<>();
        Set<String> activeUserSet = new HashSet<>();
        List<String> activeUserList = new ArrayList<>();
        List<String> deviceList = new ArrayList<>();
        Set<String> activeDeviceSet = new HashSet<>();
        List<String> activeDeviceList = new ArrayList<>();
        Map<String, Long> map = new HashMap<>();
        map.put("accessSum", accessSum);

        if ("pc".equals(deviceType) || "all".equals(deviceType)) {
            param.setDeviceType("pc");
            param.setQueryType("system");

            // 查询图表数据
            queryChartData(param, userList, activeUserList, deviceList, activeDeviceList, map);

            userSum = (long) userList.size();
            activeUserSum = (long) activeUserList.size();
            accessSum = map.get("accessSum");
            deviceSum = (long) deviceList.size();
            activeDeviceSum = (long) activeDeviceList.size();

            param.setDeviceType(deviceType);
        }

        if ("mobile".equals(deviceType) || "all".equals(deviceType)) {
            param.setDeviceType("mobile");
            // 根据系统id查询系统表app表关联表中是否存在app数据
            // StatisticMapper【13】根据系统id查询系统表app表关联表中的app
            List<MobileAppInfoDO> appList = mapper.listMobileApp(systemId);
            if (appList.isEmpty()) {
                // 不存在移动app端，查询移动web端数据
                param.setQueryType("system");
                // 查询图表数据
                queryChartData(param, userList, activeUserList, deviceList, activeDeviceList, map);
            } else {
                // 系统存在移动端app
                param.setQueryType("app");
                for (int i = 0; i < appList.size(); i++) {
                    MobileAppInfoDO app = appList.get(i);

                    Integer systemNumType = app.getSystemNumType();
                    Integer matomoSiteId = app.getMatomoSiteId();
                    param.setAppSiteId(matomoSiteId);
                    if (systemNumType == 1) {
                        // 单系统APP，此APP只对应一个业务系统
                        param.setSystemNumType(1);
                        // 查询图表数据
                        queryChartData(param, userList, activeUserList, deviceList, activeDeviceList, map);
                    } else if (systemNumType == 2) {
                        // 多系统APP，此APP对应多个业务系统
                        param.setSystemNumType(2);
                        // 查询图表数据
                        queryChartData(param, userList, activeUserList, deviceList, activeDeviceList, map);
                    }
                }
            }

            userSum = (long) userList.size();
            activeUserSum = (long) activeUserList.size();
            accessSum = map.get("accessSum");
            deviceSum = (long) deviceList.size();
            activeDeviceSum = (long) activeDeviceList.size();

            param.setDeviceType(deviceType);
        }

        if ("all".equals(deviceType)) {
            param.setDeviceType("all");
            // 查询某系统某天或某段时间全部设备类型的总用户数（去重）
            for (int i = 0; i < userList.size(); i++) {
                userSet.add(userList.get(i));
            }

            // 查询某系统某天或某段时间全部设备类型的活跃用户数（去重）
            for (int i = 0; i < activeUserList.size(); i++) {
                activeUserSet.add(activeUserList.get(i));
            }

            // 查询某系统某天或某段时间全部设备类型的访问量（直接求和）
            accessSum = map.get("accessSum");

            // 查询某系统某天或某段时间全部设备类型的总设备数（去重）
            for (int i = 0; i < deviceList.size(); i++) {
                deviceSet.add(deviceList.get(i));
            }

            // 查询某系统某天或某段时间全部设备类型的活跃设备数（去重）
            for (int i = 0; i < activeDeviceList.size(); i++) {
                activeDeviceSet.add(activeDeviceList.get(i));
            }

            userSum = (long) userSet.size();
            activeUserSum = (long) activeUserSet.size();
            deviceSum = (long) deviceSet.size();
            activeDeviceSum = (long) activeDeviceSet.size();

            param.setDeviceType(deviceType);
        }

        dto.setDeviceType(deviceType);
        dto.setTheDate(theDateStr);
        dto.setUserSum(userSum);
        dto.setActiveUserSum(activeUserSum);
        dto.setAccessSum(accessSum);
        dto.setDeviceSum(deviceSum);
        dto.setActiveDeviceSum(activeDeviceSum);

        chartDataList.add(dto);
    }

    // 查询图表数据
    public void queryChartData(MatomoMapperQuery param, List<String> userList, List<String> activeUserList, List<String> deviceList, List<String> activeDeviceList, Map<String, Long> map) {
        Long accessSum = map.get("accessSum");
        if (param == null) {
            throw new RuntimeException("queryChartData方法的查询条件MatomoMapperQuery的实例对象param不能为空");
        }
        if (userList == null || activeUserList == null || deviceList == null) {
            throw new RuntimeException("queryChartData方法的各个数据list不能为空");
        }
        if (accessSum == null) {
            throw new RuntimeException("queryChartData方法的accessSum值不能为null");
        }

        // MatomoMapper【05】查询某系统截止某日期某种设备的所有用户list
        List<String> mobileUserList = matomoMapper.listAllUser(param);
        userList.addAll(mobileUserList);

        // MatomoMapper【06】查询某系统某天或某段时间某种设备的活跃用户list
        List<String> mobileActiveUserList = matomoMapper.listActiveUser(param);
        activeUserList.addAll(mobileActiveUserList);

        // MatomoMapper【07】查询某系统某天或某段时间某种设备的访问量
        accessSum += matomoMapper.queryTheAccessSum(param);
        map.put("accessSum", accessSum);

        // MatomoMapper【10】查询某系统截止某日期某种设备的所有设备list
        List<String> mobileDeviceList = matomoMapper.listAllDevice(param);
        deviceList.addAll(mobileDeviceList);

        // MatomoMapper【08】查询某系统某天或某段时间某种设备的活跃设备list
        List<String> mobileActiveDeviceList = matomoMapper.listActiveDevice(param);
        activeDeviceList.addAll(mobileActiveDeviceList);
    }

    // statistic【03】查询系统主管list
    @Override
    public ResultDTO listSystemVip(String systemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (systemId == null || "".equals(systemId)) {
            throw new RuntimeException("系统id不能为空");
        }

        // StatisticMapper【04】查询系统主管list
        List<SystemVipDTO> list = mapper.listSystemVip(systemId);

        return new ResultDTO("1", "statistic【03】查询系统主管list成功", list);
    }

    // statistic【04】查询系统主管近一月每天的访问次数
    @Override
    public ResultDTO queryVipAccessNum(VipAcessNumQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = param.getSystemId();
        String empId = param.getEmpId();
        String deviceType = param.getDeviceType();

        List<QueryVipAccessNumVO> list = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // deviceType参数检验
        String systemUrl = deviceTypeCheck(deviceType, systemId);

        for (int i = 29; i >= 0; i--) {
            LocalDate theLocalDate = localDate.minusDays(i);
            String theDate = dtf.format(theLocalDate);

            // StatisticMapper【05】查询某系统某天某主管的访问次数
            //Long num = mapper.queryVipAccessNum(systemId, theDate, empId);
            // MatomoMapper【09】查询某系统某天某用户某种设备的访问量
            Long num = matomoMapper.queryAccessSumByUserId(systemUrl, theDate, empId, deviceType);

            QueryVipAccessNumVO vo = new QueryVipAccessNumVO();
            vo.setSystemId(systemId);
            vo.setEmpId(empId);
            vo.setTheDate(theDate);
            vo.setAccessNum(num);
            list.add(vo);
        }

        return new ResultDTO("1", "statistic【04】查询系统主管近一月每天的访问次数成功", list);
    }

    // statistic【05】查询活跃用户list(包含闲置用户list)
    @Override
    public ResultDTO listActiveUser(ListActiveUserQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = param.getSystemId();
        String dateType = param.getDateType();
        String theDate = param.getTheDate();
        String deviceType = param.getDeviceType();

        ListActiveUserVO result = new ListActiveUserVO();

        if (!"day".equals(dateType) && !"month".equals(dateType)) {
            throw new RuntimeException("日期类型只能为day和month");
        }

        // deviceType参数检验
        String systemUrl = deviceTypeCheck(deviceType, systemId);

        List<String> userIdList = new ArrayList<>();
        Set<String> userIdSet = new HashSet<>();
        List<UserInfoVO> activeUserList = new ArrayList<>();
        List<UserInfoVO> inactiveUserList = new ArrayList<>();

        List<String> activeUserIdList = new ArrayList<>();
        Set<String> activeUserIdSet = new HashSet<>();
        List<DeviceInfoVO> activeDeviceList = new ArrayList<>();
        List<DeviceInfoVO> inactiveDeviceList = new ArrayList<>();

        List<String> deviceIdList = new ArrayList<>();
        List<String> activeDeviceIdList = new ArrayList<>();
        Set<String> activeDeviceIdSet = new HashSet<>();
        Set<String> deviceIdSet = new HashSet<>();

        result.setActiveUserList(activeUserList);
        result.setInactiveUserList(inactiveUserList);
        result.setActiveDeviceList(activeDeviceList);
        result.setInactiveDeviceList(inactiveDeviceList);

        MatomoMapperQuery matomoParam = new MatomoMapperQuery();
        matomoParam.setSystemUrl(systemUrl);
        if ("day".equals(dateType)) {
            Date strDate = new SimpleDateFormat("yyyy-MM-dd").parse(theDate);
            if (strDate == null) {
                throw new RuntimeException("自定义日期范围日期只能为yyyy-MM-dd格式");
            }

            matomoParam.setDateType("theDate");
            matomoParam.setTheDate(theDate);
            // 按设备类型查询基本信息
            queryBaseInfoByDeviceType(systemId, deviceType, matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList,
                    userIdSet, activeUserIdSet, deviceIdSet, activeDeviceIdSet);
        } else if ("month".equals(dateType)) {
            Date strDate = new SimpleDateFormat("yyyy-MM").parse(theDate);
            if (strDate == null) {
                throw new RuntimeException("自定义日期范围日期只能为yyyy-MM格式");
            }

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startLocalDate = LocalDate.parse(theDate + "-01", dtf);
            LocalDate endLocalDate = startLocalDate.plusMonths(1);

            matomoParam.setDateType("timePeriod");
            matomoParam.setTheDate(theDate);
            matomoParam.setStartDate(dtf.format(startLocalDate));
            matomoParam.setEndDate(dtf.format(endLocalDate));

            // 按设备类型查询基本信息
            queryBaseInfoByDeviceType(systemId, deviceType, matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList,
                    userIdSet, activeUserIdSet, deviceIdSet, activeDeviceIdSet);
        }

        // 查询用户信息list和设备信息list
        queryBaseInfoList(userIdList, activeUserIdList, deviceIdList, activeDeviceIdList, activeUserList, inactiveUserList, activeDeviceList, inactiveDeviceList);

        result.setActiveUserNum(activeUserList.size());
        result.setInactiveUserNum(inactiveUserList.size());
        result.setActiveDeviceNum(activeDeviceList.size());
        result.setInactiveDeviceNum(inactiveDeviceList.size());

        return new ResultDTO("1", "statistic【05】查询活跃用户list(包含闲置用户list)成功", result);
    }

    // 按设备类型查询基本信息
    public void queryBaseInfoByDeviceType(String systemId, String deviceType, MatomoMapperQuery matomoParam,
                                          List<String> userIdList, List<String> activeUserIdList, List<String> deviceIdList, List<String> activeDeviceIdList,
                                          Set<String> userIdSet, Set<String> activeUserIdSet, Set<String> deviceIdSet, Set<String> activeDeviceIdSet) {
        if ("pc".equals(deviceType) || "all".equals(deviceType)) {
            matomoParam.setQueryType("system");
            matomoParam.setDeviceType("pc");

            // 查询用户idList以及设备idList
            queryBaseInfoIdList(matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList);

            matomoParam.setDeviceType(deviceType);
        }

        if ("mobile".equals(deviceType) || "all".equals(deviceType)) {
            matomoParam.setDeviceType("mobile");
            // 根据系统id查询系统表app表关联表中是否存在app数据
            // StatisticMapper【13】根据系统id查询系统表app表关联表中的app
            List<MobileAppInfoDO> appList = mapper.listMobileApp(systemId);
            if (appList.isEmpty()) {
                // 不存在移动app端，查询移动web端数据
                matomoParam.setQueryType("system");
                // 查询用户idList以及设备idList
                queryBaseInfoIdList(matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList);
            } else {
                // 系统存在移动端app
                matomoParam.setQueryType("app");
                for (int i = 0; i < appList.size(); i++) {
                    MobileAppInfoDO app = appList.get(i);

                    Integer systemNumType = app.getSystemNumType();
                    Integer matomoSiteId = app.getMatomoSiteId();
                    matomoParam.setAppSiteId(matomoSiteId);
                    if (systemNumType == 1) {
                        // 单系统APP，此APP只对应一个业务系统
                        matomoParam.setSystemNumType(1);
                        // 查询用户idList以及设备idList
                        queryBaseInfoIdList(matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList);
                    } else if (systemNumType == 2) {
                        // 多系统APP，此APP对应多个业务系统
                        matomoParam.setSystemNumType(2);
                        // 查询用户idList以及设备idList
                        queryBaseInfoIdList(matomoParam, userIdList, activeUserIdList, deviceIdList, activeDeviceIdList);
                    }
                }
            }

            matomoParam.setDeviceType(deviceType);
        }

        if ("all".equals(deviceType)) {
            matomoParam.setDeviceType("all");

            // 查询某系统某天或某段时间全部设备类型的总用户数（去重）
            for (int i = 0; i < userIdList.size(); i++) {
                userIdSet.add(userIdList.get(i));
            }
            userIdList.clear();
            for (String userId: userIdSet) {
                userIdList.add(userId);
            }

            // 查询某系统某天或某段时间全部设备类型的活跃用户数（去重）
            for (int i = 0; i < activeUserIdList.size(); i++) {
                activeUserIdSet.add(activeUserIdList.get(i));
            }
            activeUserIdList.clear();
            for (String activeUserId: activeUserIdSet) {
                activeUserIdList.add(activeUserId);
            }

            // 查询某系统某天或某段时间全部设备类型的总设备数（去重）
            for (int i = 0; i < deviceIdList.size(); i++) {
                deviceIdSet.add(deviceIdList.get(i));
            }
            deviceIdList = new ArrayList<>();
            for (String deviceId: deviceIdSet) {
                deviceIdList.add(deviceId);
            }

            // 查询某系统某天或某段时间全部设备类型的活跃设备数（去重）
            for (int i = 0; i < activeDeviceIdList.size(); i++) {
                activeDeviceIdSet.add(activeDeviceIdList.get(i));
            }
            activeDeviceIdList = new ArrayList<>();
            for (String activeDeviceId: activeDeviceIdSet) {
                activeDeviceIdList.add(activeDeviceId);
            }

            matomoParam.setDeviceType(deviceType);
        }
    }

    // 查询用户idList以及设备idList
    public void queryBaseInfoIdList(MatomoMapperQuery matomoParam, List<String> userIdList, List<String> activeUserIdList, List<String> deviceIdList, List<String> activeDeviceIdList) {
        // MatomoMapper【05】查询某系统截止某日期某种设备的所有用户list
        userIdList.addAll(matomoMapper.listAllUser(matomoParam));
        // MatomoMapper【06】查询某系统某天或某段时间某种设备的活跃用户list
        activeUserIdList.addAll(matomoMapper.listActiveUser(matomoParam));

        // MatomoMapper【10】查询某系统截止某日期某种设备的所有设备list
        deviceIdList.addAll(matomoMapper.listAllDevice(matomoParam));
        // MatomoMapper【08】查询某系统某天或某段时间某种设备的活跃设备list
        activeDeviceIdList.addAll(matomoMapper.listActiveDevice(matomoParam));
    }

    // 查询用户信息list和设备信息list
    public void queryBaseInfoList(List<String> userIdList, List<String> activeUserIdList, List<String> deviceIdList, List<String> activeDeviceIdList,
                                   List<UserInfoVO> activeUserList, List<UserInfoVO> inactiveUserList, List<DeviceInfoVO> activeDeviceList, List<DeviceInfoVO> inactiveDeviceList) {
        userIdList.removeAll(activeUserIdList);// 求补集后，userIdList现为闲置用户list
        // 查询活跃用户信息
        queryUserInfo(activeUserIdList, activeUserList);
        // 查询闲置用户信息
        queryUserInfo(userIdList, inactiveUserList);

        deviceIdList.removeAll(activeDeviceIdList);// 求补集后，deviceIdList现为闲置设备list
        // 查询活跃设备信息
        queryDeviceInfo(activeDeviceIdList, activeDeviceList);
        // 查询闲置用户信息
        queryDeviceInfo(deviceIdList, inactiveDeviceList);
    }

    // 查询设备信息
    public void queryDeviceInfo(List<String> deviceIdList, List<DeviceInfoVO> deviceInfoList) {
        for (int i = 0; i < deviceIdList.size(); i++) {
            String deviceId = deviceIdList.get(i);

            // 查询单个设备信息
            DeviceInfoVO deviceInfo = querySingleDeviceInfo(deviceId);

            deviceInfoList.add(deviceInfo);
        }
    }

    // 查询单个设备信息
    public DeviceInfoVO querySingleDeviceInfo(String deviceId) {
        DeviceInfoVO deviceInfo = new DeviceInfoVO();

        // MatomoMapper【11】查询设备信息
        DeviceInfoDTO dto = matomoMapper.queryDeviceInfo(deviceId);
        if (dto == null) {
            throw new RuntimeException("设备id=【" + deviceId + "】的设备不存在");
        }

        // MatomoMapper【12】查询设备AppId
        String deviceAppId = matomoMapper.queryDeviceAppId(deviceId);

        deviceId = dto.getDeviceId();
        String lastLoginSiteName = dto.getLastLoginSiteName();
        String lastLoginUserId = dto.getLastLoginUserId();
        String lastLoginTime = dto.getLastLoginTime();
        String osType = dto.getOsType();
        String osVersion = dto.getOsVersion();
        String resolution = dto.getResolution();

        // 查询单个用户信息
        UserInfoVO lastLoginUser = querySingleUserInfo(lastLoginUserId);

        deviceInfo.setDeviceId(deviceId);
        deviceInfo.setDeviceAppId(deviceAppId);
        deviceInfo.setLastLoginSiteName(lastLoginSiteName);
        deviceInfo.setLastLoginUser(lastLoginUser);
        deviceInfo.setLastLoginTime(lastLoginTime);
        deviceInfo.setOsType(osType);
        deviceInfo.setOsVersion(osVersion);
        deviceInfo.setResolution(resolution);

        return deviceInfo;
    }

    // 查询用户信息
    public void queryUserInfo(List<String> empIdList, List<UserInfoVO> userInfoList) {
        for (int i = 0; i < empIdList.size(); i++) {
            String empId = empIdList.get(i);

            // 查询单个用户信息
            UserInfoVO userInfo = querySingleUserInfo(empId);

            userInfoList.add(userInfo);
        }
    }

    // 查询单个用户信息
    public UserInfoVO querySingleUserInfo(String empId) {
        // StatisticMapper【10】根据工号查询用户信息
        UserInfoVO userInfo = mapper.queryUserInfo(empId);
        if (userInfo == null) {
            userInfo = new UserInfoVO();
            userInfo.setEmpId(empId);
            //throw new RuntimeException("工号：【" + empId + "】对应的用户信息为空");
        }
        String deptNo = userInfo.getDeptNo();
        if (deptNo == null || "".equals(deptNo)) {
            userInfo.setDeptNo("");
            //throw new RuntimeException("工号：【" + empId + "】对应的用户的部门代码为空");
        }
        String factory = userInfo.getFactory();
        if (factory == null || "".equals(factory)) {
            userInfo.setFactory("");
            //throw new RuntimeException("工号：【" + empId + "】对应的用户的厂区编号为空");
        }
        if (!"CD".equals(factory)) {
            factory = "SZ";
        }

        if (!"".equals(deptNo) && !"".equals(factory)) {
            // StatisticMapper【11】根据部门代码查询部门名称
            String deptName = mapper.queryDeptName(deptNo, factory);
            userInfo.setDeptName(deptName);
        } else {
            userInfo.setDeptName("");
        }

        return userInfo;
    }

    // statistic【06】根据工号或姓名查询人员信息
    @Override
    public ResultDTO queryEmpInfo(String param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (param == null || "".equals(param)) {
            throw new RuntimeException("工号或姓名不能为空");
        }

        List<UserInfoVO> list = new ArrayList<>();
        if (param.length() > 2 && param.charAt(2) >= '0' && param.charAt(2) <= '9') {
            // StatisticMapper【10】根据工号查询用户信息
            UserInfoVO userInfo = mapper.queryUserInfo(param.toUpperCase());
            if (userInfo != null) {
                list.add(userInfo);
            }
        } else {
            // StatisticMapper【12】根据姓名查询用户信息
            list = mapper.queryUserInfoByName(param);
        }

        if (list.size() == 0) {
            throw new RuntimeException("依据输入的【" + param + "】在McEBG事业群中无法找到相关的人员信息，请确认相应的工号或姓名是否输入正确（姓名需为繁体）");
        }

        for (int i = 0; i < list.size(); i++) {
            UserInfoVO userInfo = list.get(i);
            String deptNo = userInfo.getDeptNo();
            String factory = userInfo.getFactory();

            if (factory != null && !"CD".equals(factory)) {
                factory = "SZ";
            }

            if (deptNo != null && !"".equals(deptNo) && factory != null && !"".equals(factory)) {
                // StatisticMapper【11】根据部门代码查询部门名称
                String deptName = mapper.queryDeptName(deptNo, factory);
                userInfo.setDeptName(deptName);
            }
        }

        return new ResultDTO("1", "statistic【06】根据工号或姓名查询人员信息成功", list);
    }
}

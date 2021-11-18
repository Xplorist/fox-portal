package com.foxconn.systemportal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.foxconn.systemportal.mapper.StatisticMapper;
import com.foxconn.systemportal.mapper.SystemInfoMapper;
import com.foxconn.systemportal.model.SystemInfoDO;
import com.foxconn.systemportal.model.dto.ResultDTO;
import com.foxconn.systemportal.model.dto.StatisticChartDataDTO;
import com.foxconn.systemportal.model.dto.SystemInfoDTO;
import com.foxconn.systemportal.model.query.StatisticDataQuery;
import com.foxconn.systemportal.model.vo.QueryStatisticDataVO;
import com.foxconn.systemportal.service.StatisticService;
import com.foxconn.systemportal.util.DTOConverter;
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

    // statistic【01】查询系统list
    @Override
    public ResultDTO listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 查询系统list
        List<SystemInfoDO> systemInfoDOS = systemInfoMapper.selectList(new QueryWrapper<SystemInfoDO>().lambda().eq(SystemInfoDO::getValidFlag, 1).orderByAsc(SystemInfoDO::getListOrder));

        List<SystemInfoDTO> list = new ArrayList<>();

        for (int i = 0; i < systemInfoDOS.size(); i++) {
            SystemInfoDO systemInfoDO = systemInfoDOS.get(i);
            SystemInfoDTO dto = new SystemInfoDTO();
            dto = (SystemInfoDTO) DTOConverter.parseDoToDTO(systemInfoDO, dto);
            list.add(dto);
        }

        return new ResultDTO("1", "statistic【01】查询系统list成功", list);
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
            int index = dateType.indexOf("-");
            if (index <= 0) {
                throw new RuntimeException("自定义日期范围必须以‘-’（英文中划线，不是下划线）分隔");
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
        if (!"all".equals(deviceType) && !"pc".equals(deviceType) && !"mobile".equals(deviceType)) {
            throw new RuntimeException("设备类型只能为all或pc或mobile");
        }

        String nowDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        /*==查询当天==*/
        // 查询当天总用户数量
        // StatisticMapper【01】查询某系统截止某天总用户数量
        Long todayUserSum = mapper.queryRegisterUserSum(systemId, nowDateStr);
        // 查询当天活跃用户数量
        // StatisticMapper【02】查询某系统某天活跃用户数量
        Long todayActiveUserSum = mapper.queryActiveUserSum(systemId, nowDateStr);

        List<StatisticChartDataDTO> chartDataList = new ArrayList<>();

        Date nowDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long dayLong = 1000 * 60 * 60 * 24;

        if ("week".equals(dateType)) {
            // 近7天
            for (int i = 6; i >= 0; i--) {
                Date theDate = new Date(nowDate.getTime() - dayLong * i);
                String theDateStr = sdf.format(theDate);
                addChartData(systemId, chartDataList, theDateStr);
            }

        } else if ("month".equals(dateType)) {
            // 近1月
            for (int i = 29; i >= 0; i--) {
                Date theDate = new Date(nowDate.getTime() - dayLong * i);
                String theDateStr = sdf.format(theDate);
                addChartData(systemId, chartDataList, theDateStr);
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

                // StatisticMapper【01】查询某系统截止某天总用户数量
                Long userSum = mapper.queryRegisterUserSum(systemId, dtf.format(endLocal));

                // StatisticMapper【03】查询某系统某个时间段内活跃用户数量
                Long activeUserSum = mapper.queryActiveUserSumByTimePeriod(systemId, dtf.format(startLocal), dtf.format(endLocal));

                StatisticChartDataDTO dto = new StatisticChartDataDTO();
                dto.setTheDate(yearValue + "-" + monthStr);
                dto.setUserSum(userSum);
                dto.setActiveUserSum(activeUserSum);

                chartDataList.add(dto);
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
                addChartData(systemId, chartDataList, dtf.format(startDateLocal));
                startDateLocal = startDateLocal.plusDays(1);
            }
        }

        QueryStatisticDataVO vo = new QueryStatisticDataVO();
        vo.setTodayUserSum(todayUserSum);
        vo.setTodayActiveUserSum(todayActiveUserSum);
        vo.setChartDataList(chartDataList);

        return new ResultDTO("1", "statistic【02】根据条件查询系统统计数据成功", vo);
    }

    public void addChartData(String systemId, List<StatisticChartDataDTO> chartDataList, String theDateStr) {
        StatisticChartDataDTO dto = new StatisticChartDataDTO();

        // StatisticMapper【01】查询某系统截止某天总用户数量
        Long userSum = mapper.queryRegisterUserSum(systemId, theDateStr);
        // StatisticMapper【02】查询某系统某天活跃用户数量
        Long activeUserSum = mapper.queryActiveUserSum(systemId, theDateStr);

        dto.setTheDate(theDateStr);
        dto.setUserSum(userSum);
        dto.setActiveUserSum(activeUserSum);

        chartDataList.add(dto);
    }
}

package com.foxconn.mcebg.portal.service.impl;

//@Service
public class StatisticServiceImplx {
/**

 @Autowired
    StatisticMapper mapper;
    @Autowired
    SystemInfoMapper systemInfoMapper;
    @Autowired
    MatomoMapper matomoMapper;

    // statistic【01】查询系统list
    //@Override

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

    // statistic【02】根据条件查询系统统计数据
    //@Override
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

        String nowDateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //查询当天
        // 查询当天总用户数量
        // StatisticMapper【01】查询某系统截止某天总用户数量
        Long todayUserSum = mapper.queryRegisterUserSum(systemId, nowDateStr);
        // 查询当天活跃用户数量
        // StatisticMapper【02】查询某系统某天活跃用户数量
        Long todayActiveUserSum = mapper.queryActiveUserSum(systemId, nowDateStr);
        // 查询当天的访问量
        // MatomoMapper【01】查询某系统某天的访问量
        Long todayAccessSum = matomoMapper.queryAccessSum(systemUrl, nowDateStr);
        // 查询当天PC设备数量
        // MatomoMapper【03】查询某系统某天的某种设备使用数量（设备类型： PC，mobile）
        Long todayPcDeviceSum = matomoMapper.queryDeviceSum("PC", systemUrl, nowDateStr);
        // 查询当天移动设备数量
        // MatomoMapper【03】查询某系统某天的某种设备使用数量（设备类型： PC，mobile）
        Long todayMobileDeviceSum = matomoMapper.queryDeviceSum("mobile", systemUrl, nowDateStr);

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
                addChartData(systemId, systemUrl, chartDataList, theDateStr);
            }

        } else if ("month".equals(dateType)) {
            // 近1月
            for (int i = 29; i >= 0; i--) {
                Date theDate = new Date(nowDate.getTime() - dayLong * i);
                String theDateStr = sdf.format(theDate);
                addChartData(systemId, systemUrl, chartDataList, theDateStr);
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

                // MatomoMapper【02】查询某系统某段时间的访问量
                Long accessSum = matomoMapper.queryAccessSumByTimePeriod(systemUrl, dtf.format(startLocal), dtf.format(endLocal));

                // 查询某系统某段时间PC设备数量
                // MatomoMapper【04】查询某系统某段时间某种设备使用数量（设备类型： PC，mobile）
                Long pcDeviceSum = matomoMapper.queryDeviceSumByTimePeriod("PC", systemUrl, dtf.format(startLocal), dtf.format(endLocal));
                // 查询某系统某段时间移动设备数量
                // MatomoMapper【04】查询某系统某段时间某种设备使用数量（设备类型： PC，mobile）
                Long mobileDeviceSum = matomoMapper.queryDeviceSumByTimePeriod("mobile", systemUrl, dtf.format(startLocal), dtf.format(endLocal));

                StatisticChartDataDTO dto = new StatisticChartDataDTO();
                dto.setTheDate(yearValue + "-" + monthStr);
                dto.setUserSum(userSum);
                dto.setAccessSum(accessSum);
                dto.setActiveUserSum(activeUserSum);
                //dto.setPcDeviceSum(pcDeviceSum);
                //dto.setMobileDeviceSum(mobileDeviceSum);

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
                addChartData(systemId, systemUrl, chartDataList, dtf.format(startDateLocal));
                startDateLocal = startDateLocal.plusDays(1);
            }
        }

        QueryStatisticDataVO vo = new QueryStatisticDataVO();
        //vo.setTodayUserSum(todayUserSum);
        //vo.setTodayActiveUserSum(todayActiveUserSum);
        //vo.setTodayAccessSum(todayAccessSum);
        //vo.setTodayPcDeviceSum(todayPcDeviceSum);
        //vo.setTodayMobileDeviceSum(todayMobileDeviceSum);
        vo.setChartDataList(chartDataList);

        return new ResultDTO("1", "statistic【02】根据条件查询系统统计数据成功", vo);
    }

    public void addChartData(String systemId, String systemUrl, List<StatisticChartDataDTO> chartDataList, String theDateStr) {
        StatisticChartDataDTO dto = new StatisticChartDataDTO();

        // StatisticMapper【01】查询某系统截止某天总用户数量
        Long userSum = mapper.queryRegisterUserSum(systemId, theDateStr);
        // StatisticMapper【02】查询某系统某天活跃用户数量
        Long activeUserSum = mapper.queryActiveUserSum(systemId, theDateStr);
        // MatomoMapper【01】查询某系统某天的访问量
        Long accessSum = matomoMapper.queryAccessSum(systemUrl, theDateStr);
        // 查询某系统某天的移动设备数量
        // MatomoMapper【03】查询某系统某天的某种设备使用数量（设备类型： PC，mobile）
        Long mobileDeviceSum = matomoMapper.queryDeviceSum("mobile", systemUrl, theDateStr);
        // 查询某系统某天的PC设备数量
        // MatomoMapper【03】查询某系统某天的某种设备使用数量（设备类型： PC，mobile）
        Long pcDeviceSum = matomoMapper.queryDeviceSum("PC", systemUrl, theDateStr);

        dto.setTheDate(theDateStr);
        dto.setUserSum(userSum);
        dto.setAccessSum(accessSum);
        dto.setActiveUserSum(activeUserSum);
        //dto.setPcDeviceSum(pcDeviceSum);
        //dto.setMobileDeviceSum(mobileDeviceSum);

        chartDataList.add(dto);
    }

    // statistic【03】查询系统主管list
    //@Override
    public ResultDTO listSystemVip(String systemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (systemId == null || "".equals(systemId)) {
            throw new RuntimeException("系统id不能为空");
        }

        // StatisticMapper【04】查询系统主管list
        List<SystemVipDTO> list = mapper.listSystemVip(systemId);

        return new ResultDTO("1", "statistic【03】查询系统主管list成功", list);
    }

    // statistic【04】查询系统主管近一月每天的访问次数
    //@Override
    public ResultDTO queryVipAccessNum(VipAcessNumQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = param.getSystemId();
        String empId = param.getEmpId();

        List<QueryVipAccessNumVO> list = new ArrayList<>();
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 29; i >= 0; i--) {
            LocalDate theLocalDate = localDate.minusDays(i);
            String theDate = dtf.format(theLocalDate);

            // StatisticMapper【05】查询某系统某天某主管的访问次数
            Long num = mapper.queryVipAccessNum(systemId, theDate, empId);

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
    //@Override
    public ResultDTO listActiveUser(ListActiveUserQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String systemId = param.getSystemId();
        String dateType = param.getDateType();
        String theDate = param.getTheDate();

        ListActiveUserVO result = new ListActiveUserVO();

        if (!"day".equals(dateType) && !"month".equals(dateType)) {
            throw new RuntimeException("日期类型只能为day和month");
        }

        if ("day".equals(dateType)) {
            Date strDate = new SimpleDateFormat("yyyy-MM-dd").parse(theDate);
            if (strDate == null) {
                throw new RuntimeException("自定义日期范围日期只能为yyyy-MM-dd格式");
            }

            List<UserInfoVO> activeUserList = new ArrayList<>();
            List<UserInfoVO> inactiveUserList = new ArrayList<>();
            result.setActiveUserList(activeUserList);
            result.setInactiveUserList(inactiveUserList);

            // StatisticMapper【06】查询某系统某天的活跃用户
            List<String> activeList = mapper.listActiveUser(systemId, theDate);
            // 查询用户信息
            queryUserInfo(activeList, activeUserList);
            result.setActiveUserNum(activeUserList.size());

            // StatisticMapper【07】查询某系统某天的闲置用户
            List<String> inactiveList = mapper.listInactiveUser(systemId, theDate);
            // 查询用户信息
            queryUserInfo(inactiveList, inactiveUserList);
            result.setInactiveUserNum(inactiveUserList.size());
        } else if ("month".equals(dateType)) {
            Date strDate = new SimpleDateFormat("yyyy-MM").parse(theDate);
            if (strDate == null) {
                throw new RuntimeException("自定义日期范围日期只能为yyyy-MM格式");
            }

            List<UserInfoVO> activeUserList = new ArrayList<>();
            List<UserInfoVO> inactiveUserList = new ArrayList<>();
            result.setActiveUserList(activeUserList);
            result.setInactiveUserList(inactiveUserList);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startLocalDate = LocalDate.parse(theDate + "-01", dtf);
            LocalDate endLocalDate = startLocalDate.plusMonths(1);

            // StatisticMapper【08】查询某系统某个时间段的活跃用户
            List<String> activeList = mapper.listActiveUserByTimePeriod(systemId, dtf.format(startLocalDate), dtf.format(endLocalDate));
            // 查询用户信息
            queryUserInfo(activeList, activeUserList);
            result.setActiveUserNum(activeUserList.size());

            // StatisticMapper【09】查询某系统某段时间的闲置用户
            List<String> inactiveList = mapper.listInactiveUserByTimePeriod(systemId, dtf.format(startLocalDate), dtf.format(endLocalDate));
            // 查询用户信息
            queryUserInfo(inactiveList, inactiveUserList);
            result.setInactiveUserNum(inactiveUserList.size());
        }

        return new ResultDTO("1", "statistic【05】查询活跃用户list(包含闲置用户list)成功", result);
    }

    // 查询用户信息
    public void queryUserInfo(List<String> empIdList, List<UserInfoVO> userInfoList) {
        for (int i = 0; i < empIdList.size(); i++) {
            String empId = empIdList.get(i);

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

            userInfoList.add(userInfo);
        }
    }

    // statistic【06】根据工号或姓名查询人员信息
    //@Override
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
 */

}

package com.foxconn.mcebg.portal.service;

import com.foxconn.mcebg.portal.model.dto.ResultDTO;
import com.foxconn.mcebg.portal.model.query.ListActiveUserQuery;
import com.foxconn.mcebg.portal.model.query.StatisticDataQuery;
import com.foxconn.mcebg.portal.model.query.VipAcessNumQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StatisticService {
    // statistic【01】查询系统list
    ResultDTO listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【02】根据条件查询系统统计数据
    ResultDTO queryStatisticData(StatisticDataQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【03】查询系统主管list
    ResultDTO listSystemVip(String systemId, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【04】查询系统主管近一月每天的访问次数
    ResultDTO queryVipAccessNum(VipAcessNumQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【05】查询活跃用户list(包含闲置用户list)
    ResultDTO listActiveUser(ListActiveUserQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【06】根据工号或姓名查询人员信息
    ResultDTO queryEmpInfo(String param, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

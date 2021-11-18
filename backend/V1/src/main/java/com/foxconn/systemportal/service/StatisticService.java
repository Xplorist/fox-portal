package com.foxconn.systemportal.service;

import com.foxconn.systemportal.model.dto.ResultDTO;
import com.foxconn.systemportal.model.query.StatisticDataQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StatisticService {
    // statistic【01】查询系统list
    ResultDTO listSystem(HttpServletRequest request, HttpServletResponse response) throws Exception;

    // statistic【02】根据条件查询系统统计数据
    ResultDTO queryStatisticData(StatisticDataQuery param, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

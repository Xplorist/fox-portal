package com.foxconn.systemportal.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticMapper {
    // StatisticMapper【01】查询某系统截止某天总用户数量
    Long queryRegisterUserSum(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【02】查询某系统某天活跃用户数量
    Long queryActiveUserSum(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【03】查询某系统某个时间段内活跃用户数量
    Long queryActiveUserSumByTimePeriod(@Param("systemId") String systemId, @Param("startDate") String startDate, @Param("endDate") String endDate);


}

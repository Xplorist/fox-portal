package com.foxconn.mcebg.portal.mapper.base;

import com.foxconn.mcebg.portal.model.MobileAppInfoDO;
import com.foxconn.mcebg.portal.model.dto.SystemVipDTO;
import com.foxconn.mcebg.portal.model.vo.UserInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticMapper {
    // StatisticMapper【01】查询某系统截止某天总用户数量
    Long queryRegisterUserSum(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【02】查询某系统某天活跃用户数量
    Long queryActiveUserSum(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【03】查询某系统某个时间段内活跃用户数量
    Long queryActiveUserSumByTimePeriod(@Param("systemId") String systemId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // StatisticMapper【04】查询系统主管list
    List<SystemVipDTO> listSystemVip(String systemId);

    // StatisticMapper【05】查询某系统某天某主管的访问次数
    Long queryVipAccessNum(@Param("systemId") String systemId, @Param("theDate") String theDate, @Param("empId") String empId);

    // StatisticMapper【06】查询某系统某天的活跃用户
    List<String> listActiveUser(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【07】查询某系统某天的闲置用户
    List<String> listInactiveUser(@Param("systemId") String systemId, @Param("theDate") String theDate);

    // StatisticMapper【08】查询某系统某个时间段的活跃用户
    List<String> listActiveUserByTimePeriod(@Param("systemId") String systemId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // StatisticMapper【09】查询某系统某段时间的闲置用户
    List<String> listInactiveUserByTimePeriod(@Param("systemId") String systemId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // StatisticMapper【10】根据工号查询用户信息
    UserInfoVO queryUserInfo(String empId);

    // StatisticMapper【11】根据部门代码查询部门名称
    String queryDeptName(@Param("deptNo") String deptNo, @Param("factory") String factory);

    // StatisticMapper【12】根据姓名查询用户信息
    List<UserInfoVO> queryUserInfoByName(String empName);

    // StatisticMapper【13】根据系统id查询系统表app表关联表中的app
    List<MobileAppInfoDO> listMobileApp(String systemId);

    // StatisticMapper【1x】模板
}

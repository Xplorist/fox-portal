package com.foxconn.mcebg.portal.mapper.follow;

import com.foxconn.mcebg.portal.model.dto.DeviceInfoDTO;
import com.foxconn.mcebg.portal.model.query.MatomoMapperQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatomoMapper {
    // MatomoMapper【01】查询某系统某天的访问量
    Long queryAccessSum(@Param("systemUrl") String systemUrl, @Param("theDate") String theDate);

    // MatomoMapper【02】查询某系统某段时间的访问量
    Long queryAccessSumByTimePeriod(@Param("systemUrl") String systemUrl, @Param("startDate") String startDate, @Param("endDate") String endDate);

    // MatomoMapper【03】查询某系统某天的某种设备使用数量（设备类型： PC，mobile）
    Long queryDeviceSum(@Param("deviceType") String deviceType, @Param("systemUrl") String systemUrl, @Param("theDate") String theDate);

    // MatomoMapper【04】查询某系统某段时间某种设备使用数量（设备类型： PC，mobile）
    Long queryDeviceSumByTimePeriod(@Param("deviceType") String deviceType, @Param("systemUrl") String systemUrl,  @Param("startDate") String startDate, @Param("endDate") String endDate);

    // MatomoMapper【05】查询某系统截止某日期某种设备的所有用户list
    List<String> listAllUser(MatomoMapperQuery param);

    // MatomoMapper【06】查询某系统某天或某段时间某种设备的活跃用户list
    List<String> listActiveUser(MatomoMapperQuery param);

    // MatomoMapper【07】查询某系统某天或某段时间某种设备的访问量
    Long queryTheAccessSum(MatomoMapperQuery param);

    // MatomoMapper【08】查询某系统某天或某段时间某种设备的活跃设备list
    List<String> listActiveDevice(MatomoMapperQuery param);

    // MatomoMapper【09】查询某系统某天某用户某种设备的访问量
    Long queryAccessSumByUserId(@Param("systemUrl") String systemUrl, @Param("theDate") String theDate, @Param("userId") String userId, @Param("deviceType") String deviceType);

    // MatomoMapper【10】查询某系统截止某日期某种设备的所有设备list
    List<String> listAllDevice(MatomoMapperQuery param);

    // MatomoMapper【11】查询设备信息
    DeviceInfoDTO queryDeviceInfo(String deviceId);

    // MatomoMapper【12】查询设备AppId
    String queryDeviceAppId(String deviceId);

    // MatomoMapper【1x】
}

package com.foxconn.mcebg.portal.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description="设备信息VO")
public class DeviceInfoVO {
    @ApiModelProperty(value = "设备id")
    private String deviceId;// 设备id
    @ApiModelProperty(value = "设备AppId")
    private String deviceAppId;// 设备AppId
    @ApiModelProperty(value = "最后登录的系统或App")
    private String lastLoginSiteName;// 最后登录的系统或App
    @ApiModelProperty(value = "最后登录的用户")
    private UserInfoVO lastLoginUser;// 最后登录的用户
    @ApiModelProperty(value = "最后登录的时间点")
    private String lastLoginTime;// 最后登录的时间点
    @ApiModelProperty(value = "操作系统类型")
    private String osType;// 操作系统类型
    @ApiModelProperty(value = "操作系统版本")
    private String osVersion;// 操作系统版本
    @ApiModelProperty(value = "设备分辨率")
    private String resolution;// 设备分辨率
}

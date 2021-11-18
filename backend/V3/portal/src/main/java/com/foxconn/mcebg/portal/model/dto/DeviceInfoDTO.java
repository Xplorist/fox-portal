package com.foxconn.mcebg.portal.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceInfoDTO {
    private String deviceId;// 设备id
    private String lastLoginSiteName;// 最后登录的系统或App
    private String lastLoginUserId;// 最后登录的用户id
    private String lastLoginTime;// 最后登录的时间点
    private String osType;// 操作系统类型
    private String osVersion;// 操作系统版本
    private String resolution;// 分辨率
}

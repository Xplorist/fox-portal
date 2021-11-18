package com.foxconn.mcebg.portal.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description="statistic【05】查询活跃用户list(包含闲置用户list) VO")
@Data
@NoArgsConstructor
public class ListActiveUserVO {
    @ApiModelProperty(value = "活跃用户数量")
    private Integer activeUserNum;// 活跃用户数量
    @ApiModelProperty(value = "活跃用户list")
    private List<UserInfoVO> activeUserList;// 活跃用户list
    @ApiModelProperty(value = "闲置用户数量")
    private Integer inactiveUserNum;// 闲置用户数量
    @ApiModelProperty(value = "闲置用户list")
    private List<UserInfoVO> inactiveUserList;// 闲置用户list
    @ApiModelProperty(value = "活跃设备数量")
    private Integer activeDeviceNum;// 活跃设备数量
    @ApiModelProperty(value = "活跃设备list")
    private List<DeviceInfoVO> activeDeviceList;// 活跃设备list
    @ApiModelProperty(value = "闲置设备数量")
    private Integer inactiveDeviceNum;// 闲置设备数量
    @ApiModelProperty(value = "闲置设备list")
    private List<DeviceInfoVO> inactiveDeviceList;// 闲置设备list
}

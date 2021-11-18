package com.foxconn.mcebg.portal.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticChartDataDTO {
    @ApiModelProperty("设备类型")
    private String deviceType;
    @ApiModelProperty("日期")
    private String theDate;
    @ApiModelProperty("总用户数（活跃+不活跃）")
    private Long userSum;
    @ApiModelProperty("活跃用户数")
    private Long activeUserSum;
    @ApiModelProperty("访问量")
    private Long accessSum;
    @ApiModelProperty("总设备数量（活跃+不活跃）")
    private Long deviceSum;
    @ApiModelProperty("活跃设备数量")
    private Long activeDeviceSum;
    //@ApiModelProperty("PC设备数量")
    //private Long pcDeviceSum;
    //@ApiModelProperty("移动设备数量")
    //private Long mobileDeviceSum;
}

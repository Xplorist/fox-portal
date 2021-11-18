package com.foxconn.systemportal.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticChartDataDTO {
    @ApiModelProperty("日期")
    private String theDate;
    @ApiModelProperty("用户总数")
    private Long userSum;
    @ApiModelProperty("活跃用户数")
    private Long activeUserSum;
}

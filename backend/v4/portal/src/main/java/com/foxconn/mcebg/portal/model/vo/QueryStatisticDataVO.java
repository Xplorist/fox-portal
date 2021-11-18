package com.foxconn.mcebg.portal.model.vo;

import com.foxconn.mcebg.portal.model.dto.StatisticChartDataDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description="statistic【02】根据条件查询系统统计数据接口 VO")
@Data
@NoArgsConstructor
public class QueryStatisticDataVO {
    //@ApiModelProperty(value = "今日用户总数")
    //private Long todayUserSum;
    //@ApiModelProperty(value = "今日活跃用户数")
    //private Long todayActiveUserSum;
    //@ApiModelProperty(value = "今日访问量")
    //private Long todayAccessSum;
    //@ApiModelProperty(value = "今日PC设备数量")
    //private Long todayPcDeviceSum;
    //@ApiModelProperty(value = "今日移动设备数量")
    //private Long todayMobileDeviceSum;
    @ApiModelProperty(value = "图表数据list")
    private List<StatisticChartDataDTO> chartDataList;

}

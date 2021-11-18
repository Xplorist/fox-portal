package com.foxconn.mcebg.portal.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "statistic【02】根据条件查询系统统计数据 接口Query对象")
@Data
@NoArgsConstructor
public class StatisticDataQuery {
    @NotNull(message = "系统id不能为null")
    @NotEmpty(message = "系统id不能为空字符串")
    @ApiModelProperty(value = "系统id",example="A11B902ADB06CCE8E050F40A5BE72308",required=true)
    private String systemId;// 系统id
    @NotNull(message = "日期类型不能为null")
    @NotEmpty(message = "日期类型不能为空字符串")
    @ApiModelProperty(value = "日期类型, 近7天：week,近一月：month, 近一年：year, 自定义日期范围：开始日期,结束日期 例：2020-05-26,2020-05-27",example="week",required=true)
    private String dateType;// 日期类型，近7天：week,近一月：month, 近一年：year, 自定义日期范围：开始日期-结束日期
    @NotNull(message = "设备类型不能为null")
    @NotEmpty(message = "设备类型不能为空字符串")
    @ApiModelProperty(value = "设备类型,所有：all, PC：pc，无线：mobile",example="all",required=true)
    private String deviceType;// 设备类型，所有：all, PC：pc，无线：mobile
}

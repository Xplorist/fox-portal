package com.foxconn.mcebg.portal.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel(description = "statistic【05】查询活跃用户list(包含闲置用户list) query")
public class ListActiveUserQuery {
    @NotNull(message = "系统id不能为null")
    @NotEmpty(message = "系统id不能为空字符串")
    @ApiModelProperty(value = "系统id",example="A981B2A4C85E421EE050F40A5BE7165A",required=true)
    private String systemId;// 系统id
    @NotNull(message = "日期类型不能为null")
    @NotEmpty(message = "日期类型不能为空字符串")
    @ApiModelProperty(value = "日期类型，day:天，month:月",example="day",required=true)
    private String dateType;// 日期类型，day:天，month:月
    @NotNull(message = "日期不能为null")
    @NotEmpty(message = "日期不能为空字符串")
    @ApiModelProperty(value = "日期，1.类型为day时，格式为2020-07-29，2.类型为month时，格式为2020-07",example="2020-07-29",required=true)
    private String theDate;// 日期，1.类型为day时，格式为2020-07-29，2.类型为month时，格式为2020-07
    @NotNull(message = "设备类型不能为null")
    @NotEmpty(message = "设备类型不能为空字符串")
    @ApiModelProperty(value = "设备类型,所有：all, PC：pc，无线：mobile", example = "all", required = true)
    private String deviceType;// 设备类型，所有：all, PC：pc，无线：mobile
}

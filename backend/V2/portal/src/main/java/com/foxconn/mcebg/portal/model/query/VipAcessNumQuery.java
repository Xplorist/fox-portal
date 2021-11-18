package com.foxconn.mcebg.portal.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "【04】查询系统主管近一月每天的访问次数Query")
@Data
@NoArgsConstructor
public class VipAcessNumQuery {
    @NotNull(message = "系统id不能为null")
    @NotEmpty(message = "系统id不能为空字符串")
    @ApiModelProperty(value = "系统id", example = "A11B902ADB06CCE8E050F40A5BE72308", required = true)
    private String systemId;// 系统id
    @NotNull(message = "主管工号不能为null")
    @NotEmpty(message = "主管工号不能为空字符串")
    @ApiModelProperty(value = "主管工号", example = "F3837983", required = true)
    private String empId;// 主管工号
    @NotNull(message = "设备类型不能为null")
    @NotEmpty(message = "设备类型不能为空字符串")
    @ApiModelProperty(value = "设备类型,所有：all, PC：pc，无线：mobile", example = "all", required = true)
    private String deviceType;// 设备类型,所有：all, PC：pc，无线：mobile
}

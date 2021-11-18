package com.foxconn.mcebg.portal.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description="statistic【04】查询系统主管近一月每天的访问次数 VO")
@Data
@NoArgsConstructor
public class QueryVipAccessNumVO {
    @ApiModelProperty(value = "系统id")
    private String systemId;// 系统id
    @ApiModelProperty(value = "主管工号")
    private String empId;// 主管工号
    @ApiModelProperty(value = "日期（某天）")
    private String theDate;// 日期（某天）
    @ApiModelProperty(value = "访问次数")
    private Long accessNum;// 访问次数
}

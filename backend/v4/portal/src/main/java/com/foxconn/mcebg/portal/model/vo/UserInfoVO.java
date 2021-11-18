package com.foxconn.mcebg.portal.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description="用户信息 VO")
@Data
@NoArgsConstructor
public class UserInfoVO {
    @ApiModelProperty(value = "人员工号")
    private String empId;// 人员工号
    @ApiModelProperty(value = "人员姓名")
    private String empName;// 人员姓名
    @ApiModelProperty(value = "性别")
    private String sex;// 性别
    @ApiModelProperty(value = "职位")
    private String postNo;// 职位
    @ApiModelProperty(value = "部门代码")
    private String deptNo;// 部门代码
    @ApiModelProperty(value = "部门名称")
    private String deptName;// 部门名称
    @ApiModelProperty(value = "厂区")
    private String humFactory;// 厂区
    @ApiModelProperty(value = "厂区编号")
    private String factory;// 厂区编号
    @ApiModelProperty(value = "邮箱")
    private String email;// 邮箱
}

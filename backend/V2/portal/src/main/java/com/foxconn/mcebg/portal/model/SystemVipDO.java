package com.foxconn.mcebg.portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Wed Jun 24 09:00:16 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("SYSTEM_VIP")
public class SystemVipDO implements Serializable  {

	 private static final long serialVersionUID =  8941836749285573021L;
	 /** id **/
	 @ApiModelProperty("系统主管表id")
	private String id;
	 /** 系统信息表id **/
	 @ApiModelProperty("系统信息表id")
	private String systemInfoId;
	 /** 工号 **/
	 @ApiModelProperty("工号")
	private String empId;
	 /** 姓名 **/
	 @ApiModelProperty("姓名")
	private String empName;
	 /** 创建时间 **/
	 @ApiModelProperty("创建时间")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	 /** 有效标志(0：无效，1：有效) **/
	 @ApiModelProperty("有效标志(0：无效，1：有效)")
	private Integer validFlag;
	/**  列表序号 **/
	@ApiModelProperty("列表序号")
	private Integer listOrder;
}

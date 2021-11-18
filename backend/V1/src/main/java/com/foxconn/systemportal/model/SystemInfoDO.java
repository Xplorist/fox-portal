package com.foxconn.systemportal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Mon May 25 15:26:59 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("SYSTEM_INFO")
public class SystemInfoDO implements Serializable  {

	 private static final long serialVersionUID =  3414370495132025729L;
	 /** id **/
	 @ApiModelProperty("系统id")
	private String id;
	 /** 名称 **/
	 @ApiModelProperty("系统名称")
	private String name;
	 /** 系统URL **/
	 @ApiModelProperty("系统URL")
	private String url;
	 /** 图标地址 **/
	 @ApiModelProperty("系统图标地址")
	private String picSrc;
	 /** 系统简介 **/
	 @ApiModelProperty("系统简介")
	private String summary;
	 /** 列表序号 **/
	 @ApiModelProperty("列表序号")
	private Integer listOrder;
	 /** 创建时间 **/
	 @ApiModelProperty("创建时间")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	 /** 修改时间 **/
	 @ApiModelProperty("修改时间")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;
	 /** 有效标志(0:无效，1：有效) **/
	 @ApiModelProperty("有效标志(0:无效，1：有效)")
	private Integer validFlag;


}

package com.foxconn.mcebg.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Wed Jul 29 15:59:19 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("MOBILE_APP_INFO")
public class MobileAppInfoDO implements Serializable  {

	 private static final long serialVersionUID =  1587663988333560298L;
	 /** id **/
	private String id;
	 /** Matomo系统siteId **/
	private Integer matomoSiteId;
	 /** 名称 **/
	private String name;
	 /** url(实际不存在，用来查询数据) **/
	private String url;
	 /** app类型(1:单系统APP，2:多系统app) **/
	private Integer systemNumType;
	 /** 图标地址 **/
	private String picSrc;
	 /** app简介 **/
	private String summary;
	 /** 列表序号 **/
	private Integer listOrder;
	 /** 创建时间 **/
	private Date createTime;
	 /** 修改时间 **/
	private Date updateTime;
	 /** 有效标志(0:无效，1：有效) **/
	private Integer validFlag;


}

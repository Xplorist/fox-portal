package com.foxconn.systemportal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Mon May 25 15:28:12 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("REGISTER_USER")
public class RegisterUserDO implements Serializable  {

	 private static final long serialVersionUID =  3213032976264144027L;
	 /** id **/
	private String id;
	 /** 系统信息表id **/
	private String systemInfoId;
	 /** 工号 **/
	private String empId;
	 /** 姓名 **/
	private String empName;
	 /** 注册日期 **/
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date registerDate;
	 /** 创建时间 **/
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;


}

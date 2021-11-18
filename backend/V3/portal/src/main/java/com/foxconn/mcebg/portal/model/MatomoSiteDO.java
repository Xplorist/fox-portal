package com.foxconn.mcebg.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description  
 * <p>Date: Fri Jul 10 11:13:18 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("matomo_site")
public class MatomoSiteDO implements Serializable  {

	 private static final long serialVersionUID =  2980510420731687708L;
	private long idsite;
	private String name;
	private String mainUrl;
	private java.sql.Timestamp tsCreated;
	private long ecommerce;
	private long sitesearch;
	private String sitesearchKeywordParameters;
	private String sitesearchCategoryParameters;
	private String timezone;
	private String currency;
	private long excludeUnknownUrls;
	private String excludedIps;
	private String excludedParameters;
	private String excludedUserAgents;
	private String group;
	private String type;
	private long keepUrlFragment;
	private String creatorLogin;


}

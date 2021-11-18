package com.foxconn.mcebg.portal.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * <p>Date: Wed Jul 29 16:04:34 CST 2020.</p>
 * @author user 
 */

@Data
@NoArgsConstructor
@TableName("matomo_log_visit")
public class MatomoLogVisitDO implements Serializable  {

	 private static final long serialVersionUID =  908991571150561301L;
	private long idvisit;
	private long idsite;
	private String idvisitor;
	private java.sql.Timestamp visitLastActionTime;
	private String configId;
	private String locationIp;
	private String userId;
	private java.sql.Timestamp visitFirstActionTime;
	private long visitGoalBuyer;
	private long visitGoalConverted;
	private long visitorDaysSinceFirst;
	private long visitorDaysSinceOrder;
	private long visitorReturning;
	private long visitorCountVisits;
	private long visitEntryIdactionName;
	private long visitEntryIdactionUrl;
	private long visitExitIdactionName;
	private long visitExitIdactionUrl;
	private long visitTotalActions;
	private long visitTotalInteractions;
	private long visitTotalSearches;
	private String refererKeyword;
	private String refererName;
	private long refererType;
	private String refererUrl;
	private String locationBrowserLang;
	private String configBrowserEngine;
	private String configBrowserName;
	private String configBrowserVersion;
	private String configDeviceBrand;
	private String configDeviceModel;
	private long configDeviceType;
	private String configOs;
	private String configOsVersion;
	private long visitTotalEvents;
	private Date visitorLocaltime;
	private long visitorDaysSinceLast;
	private String configResolution;
	private long configCookie;
	private long configDirector;
	private long configFlash;
	private long configGears;
	private long configJava;
	private long configPdf;
	private long configQuicktime;
	private long configRealplayer;
	private long configSilverlight;
	private long configWindowsmedia;
	private long visitTotalTime;
	private String locationCity;
	private String locationCountry;
	private double locationLatitude;
	private double locationLongitude;
	private String locationRegion;
	private String customVarK1;
	private String customVarV1;
	private String customVarK2;
	private String customVarV2;
	private String customVarK3;
	private String customVarV3;
	private String customVarK4;
	private String customVarV4;
	private String customVarK5;
	private String customVarV5;
	private long lastIdlinkVa;
	private String customDimension1;
	private String customDimension2;
	private String customDimension3;
	private String customDimension4;
	private String customDimension5;


}

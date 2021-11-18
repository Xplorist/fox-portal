package com.foxconn.mcebg.portal.model.query;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatomoMapperQuery {
    private String systemUrl;// 【非空】业务系统url
    private String queryType;//【非空】 查询类型，system:系统统计查询，app:APP统计查询
    private Integer appSiteId;// 【queryType=app时非空】app对应的matomo系统中的siteId
    private Integer systemNumType;//【queryType=app时非空】 App类型，1:单系统APP，2：多系统APP
    private String deviceType;// 【非空】设备类型，pc:PC设备，mobile:移动设备
    private String dateType;// 【非空】日期类型，theDate:某天，timePeriod:某段时间
    private String theDate;// 【dateType=theDate时非空】对应dateType为theDate时的某天
    private String startDate;// 【dateType=timePeriod时非空】对应dateType为timePeriod时的开始日期
    private String endDate;// 【dateType=timePeriod时非空】对应dateType为timePeriod时的结束日期
}

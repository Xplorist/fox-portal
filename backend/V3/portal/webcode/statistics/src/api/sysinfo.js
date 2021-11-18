// const ip = 'http://10.244.186.86:8081'

/**
 * @description 查询系统list
*/
export const listSystem={
  url:"/statistic/listSystem",
  method:"GET",
  params:{}
}

/**
 * @description 根据条件查询系统统计数据
 * @dateType 日期类型, 近7天：week,近一月：month, 近一年：year, 自定义日期范围：开始日期-结束日期
 *@deviceType 设备类型,所有：all, PC：pc，无线：mobile
 *@systemId 系统id MacI_ePDM
 */
export const queryStatisticData={
  url:"/statistic/queryStatisticData",
  method:"POST",
  data:{}
}

/**
 * @description 查询系统主管list
 * @param systemId  String 系統id
*/
export const listSystemVip={
  url:'/statistic/listSystemVip',
  method:"GET",
  params:{
    systemId:''
  }
}

/**
 * @description 查询系统主管近一月每天的访问次数
 * @param empId    String 主管工号
 * @param systemId String 系統id
*/
export const queryVipAccessNum={
  url:'/statistic/queryVipAccessNum',
  method:"POST",
  data:{
    systemId:'',
    empId:''
  }
}
/**
 * @description 查询活跃用户list(包含闲置用户list)
 * @param dateType	string 日期类型，day:天，month:月		true	
 * @param systemId string 系统id true
 * @param theDate string 日期(类型为day时，格式为2020-06-29，2.类型为month时，格式为2020-06	) true
*/
export const listActiveUser ={
  url:'/statistic/listActiveUser',
  method:'POST',
  data:{}
}
/**
 * @description 根据工号或姓名查询人员信息
 * @param param string 姓名 精确查询
*/
export const queryEmpInfo={
  url:"/statistic/queryEmpInfo",
  method:"GET",
  params:{
    param:{}
  }
}



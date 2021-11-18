export class ListActiveUserQuery {
  /** 日期类型，day:天，month:月 */
  dateType = '';

  /** 系统id */
  systemId = '';

  /** 日期，1.类型为day时，格式为2020-06-29，2.类型为month时，格式为2020-06 */
  theDate = '';
}

export class ListActiveUserVO {
  /** 活跃用户list */
  activeUserList = [];

  /** 活跃用户数量 */
  activeUserNum = undefined;

  /** 闲置用户list */
  inactiveUserList = [];

  /** 闲置用户数量 */
  inactiveUserNum = undefined;
}

export class QueryStatisticDataVO {
  /** 图表数据list */
  chartDataList = [];

  /** 今日活跃用户数 */
  todayActiveUserSum = undefined;

  /** 今日用户总数 */
  todayUserSum = undefined;
}

export class QueryVipAccessNumVO {
  /** 访问次数 */
  accessNum = undefined;

  /** 主管工号 */
  empId = '';

  /** 系统id */
  systemId = '';

  /** 日期（某天） */
  theDate = '';
}

export class ResultDTO {
  /** 结果响应编码, 0:失败，1：成功, 500:服务器发生错误 */
  code = '';

  /** 结果响应数据对象 */
  data = new ListActiveUserVO();

  /** 结果响应消息 */
  msg = '';
}

export class StatisticChartDataDTO {
  /** 活跃用户数 */
  activeUserSum = undefined;

  /** 日期 */
  theDate = '';

  /** 用户总数 */
  userSum = undefined;
}

export class StatisticDataQuery {
  /** 日期类型, 近7天：week,近一月：month, 近一年：year, 自定义日期范围：开始日期,结束日期 例：2020-05-26,2020-05-27 */
  dateType = '';

  /** 设备类型,所有：all, PC：pc，无线：mobile */
  deviceType = '';

  /** 系统id */
  systemId = '';
}

export class SystemInfoDTO {
  /** 创建时间 */
  createTime = '';

  /** 系统id */
  id = '';

  /** 列表序号 */
  listOrder = undefined;

  /** 系统名称 */
  name = '';

  /** 系统图标地址 */
  picSrc = '';

  /** 系统简介 */
  summary = '';

  /** 修改时间 */
  updateTime = '';

  /** 系统URL */
  url = '';

  /** 有效标志(0:无效，1：有效) */
  validFlag = undefined;
}

export class SystemVipDTO {
  /** 创建时间 */
  createTime = '';

  /** 工号 */
  empId = '';

  /** 姓名 */
  empName = '';

  /** 系统主管表id */
  id = '';

  /** 列表序号 */
  listOrder = undefined;

  /** 系统信息表id */
  systemInfoId = '';

  /** 有效标志(0：无效，1：有效) */
  validFlag = undefined;
}

export class UserInfoVO {
  /** 部门名称 */
  deptName = '';

  /** 部门代码 */
  deptNo = '';

  /** 人员工号 */
  empId = '';

  /** 人员姓名 */
  empName = '';

  /** 厂区编号 */
  factory = '';

  /** 厂区 */
  humFactory = '';

  /** 职位 */
  postNo = '';

  /** 性别 */
  sex = '';
}

export class VipAcessNumQuery {
  /** 主管工号 */
  empId = '';

  /** 系统id */
  systemId = '';
}

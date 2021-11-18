type ObjectMap<Key extends string | number | symbol = any, Value = any> = {
  [key in Key]: Value;
};

declare namespace defs {
  export class ListActiveUserQuery {
    /** 日期类型，day:天，month:月 */
    dateType?: string;

    /** 系统id */
    systemId?: string;

    /** 日期，1.类型为day时，格式为2020-06-29，2.类型为month时，格式为2020-06 */
    theDate?: string;
  }

  export class ListActiveUserVO {
    /** 活跃用户list */
    activeUserList?: Array<defs.UserInfoVO>;

    /** 活跃用户数量 */
    activeUserNum?: number;

    /** 闲置用户list */
    inactiveUserList?: Array<defs.UserInfoVO>;

    /** 闲置用户数量 */
    inactiveUserNum?: number;
  }

  export class QueryStatisticDataVO {
    /** 图表数据list */
    chartDataList?: Array<defs.StatisticChartDataDTO>;

    /** 今日活跃用户数 */
    todayActiveUserSum?: number;

    /** 今日用户总数 */
    todayUserSum?: number;
  }

  export class QueryVipAccessNumVO {
    /** 访问次数 */
    accessNum?: number;

    /** 主管工号 */
    empId?: string;

    /** 系统id */
    systemId?: string;

    /** 日期（某天） */
    theDate?: string;
  }

  export class ResultDTO<T0 = any> {
    /** 结果响应编码, 0:失败，1：成功, 500:服务器发生错误 */
    code?: string;

    /** 结果响应数据对象 */
    data?: T0;

    /** 结果响应消息 */
    msg?: string;
  }

  export class StatisticChartDataDTO {
    /** 活跃用户数 */
    activeUserSum?: number;

    /** 日期 */
    theDate?: string;

    /** 用户总数 */
    userSum?: number;
  }

  export class StatisticDataQuery {
    /** 日期类型, 近7天：week,近一月：month, 近一年：year, 自定义日期范围：开始日期,结束日期 例：2020-05-26,2020-05-27 */
    dateType?: string;

    /** 设备类型,所有：all, PC：pc，无线：mobile */
    deviceType?: string;

    /** 系统id */
    systemId?: string;
  }

  export class SystemInfoDTO {
    /** 创建时间 */
    createTime?: string;

    /** 系统id */
    id?: string;

    /** 列表序号 */
    listOrder?: number;

    /** 系统名称 */
    name?: string;

    /** 系统图标地址 */
    picSrc?: string;

    /** 系统简介 */
    summary?: string;

    /** 修改时间 */
    updateTime?: string;

    /** 系统URL */
    url?: string;

    /** 有效标志(0:无效，1：有效) */
    validFlag?: number;
  }

  export class SystemVipDTO {
    /** 创建时间 */
    createTime?: string;

    /** 工号 */
    empId?: string;

    /** 姓名 */
    empName?: string;

    /** 系统主管表id */
    id?: string;

    /** 列表序号 */
    listOrder?: number;

    /** 系统信息表id */
    systemInfoId?: string;

    /** 有效标志(0：无效，1：有效) */
    validFlag?: number;
  }

  export class UserInfoVO {
    /** 部门名称 */
    deptName?: string;

    /** 部门代码 */
    deptNo?: string;

    /** 人员工号 */
    empId?: string;

    /** 人员姓名 */
    empName?: string;

    /** 厂区编号 */
    factory?: string;

    /** 厂区 */
    humFactory?: string;

    /** 职位 */
    postNo?: string;

    /** 性别 */
    sex?: string;
  }

  export class VipAcessNumQuery {
    /** 主管工号 */
    empId?: string;

    /** 系统id */
    systemId?: string;
  }
}

declare namespace API {
  /**
   * 系统统计API
   */
  export namespace statistic {
    /**
     * 【05】查询活跃用户list(包含闲置用户list)
     * /statistic/listActiveUser
     */
    export namespace listActiveUser {
      export class Params {}

      export type Response = defs.ResultDTO<defs.ListActiveUserVO>;

      export const init: Response;

      export function request(
        params: Params,
        body: defs.ListActiveUserQuery,
        options?: any,
      ): Promise<Response>;
    }

    /**
     * 【01】查询系统list
     * /statistic/listSystem
     */
    export namespace listSystem {
      export class Params {}

      export type Response = defs.ResultDTO<Array<defs.SystemInfoDTO>>;

      export const init: Response;

      export function request(params: Params, options?: any): Promise<Response>;
    }

    /**
     * 【03】查询系统主管list
     * /statistic/listSystemVip
     */
    export namespace listSystemVip {
      export class Params {
        /** systemId */
        systemId?: string;
      }

      export type Response = defs.ResultDTO<Array<defs.SystemVipDTO>>;

      export const init: Response;

      export function request(params: Params, options?: any): Promise<Response>;
    }

    /**
     * 【06】根据工号或姓名查询人员信息
     * /statistic/queryEmpInfo
     */
    export namespace queryEmpInfo {
      export class Params {
        /** param */
        param?: string;
      }

      export type Response = defs.ResultDTO<Array<defs.UserInfoVO>>;

      export const init: Response;

      export function request(params: Params, options?: any): Promise<Response>;
    }

    /**
     * 【02】根据条件查询系统统计数据
     * /statistic/queryStatisticData
     */
    export namespace queryStatisticData {
      export class Params {}

      export type Response = defs.ResultDTO<defs.QueryStatisticDataVO>;

      export const init: Response;

      export function request(
        params: Params,
        body: defs.StatisticDataQuery,
        options?: any,
      ): Promise<Response>;
    }

    /**
     * 【04】查询系统主管近一月每天的访问次数
     * /statistic/queryVipAccessNum
     */
    export namespace queryVipAccessNum {
      export class Params {}

      export type Response = defs.ResultDTO<Array<defs.QueryVipAccessNumVO>>;

      export const init: Response;

      export function request(
        params: Params,
        body: defs.VipAcessNumQuery,
        options?: any,
      ): Promise<Response>;
    }

    /**
     * 【0x】模板
     * /statistic/template
     */
    export namespace template {
      export class Params {
        /** param */
        param?: string;
      }

      export type Response = defs.ResultDTO<ObjectMap>;

      export const init: Response;

      export function request(params: Params, options?: any): Promise<Response>;
    }
  }
}

/**
 * @desc 【04】查询系统主管近一月每天的访问次数
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {}

export const init = new defs.ResultDTO();

export function request(
  params: Params,
  body: defs.VipAcessNumQuery,
  options?: any,
) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/queryVipAccessNum', params, 'POST'),
    {
      method: 'POST',

      body,
      ...options,
    },
  );
}

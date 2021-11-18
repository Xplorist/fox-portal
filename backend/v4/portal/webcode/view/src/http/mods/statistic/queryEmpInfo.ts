/**
 * @desc 【06】根据工号或姓名查询人员信息
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {
  /** param */
  param?: string;
}

export const init = new defs.ResultDTO();

export function request(params: Params, options?: any) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/queryEmpInfo', params, 'GET'),
    {
      method: 'GET',

      ...options,
    },
  );
}

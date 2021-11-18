/**
 * @desc 【03】查询系统主管list
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {
  /** systemId */
  systemId?: string;
}

export const init = new defs.ResultDTO();

export function request(params: Params, options?: any) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/listSystemVip', params, 'GET'),
    {
      method: 'GET',

      ...options,
    },
  );
}

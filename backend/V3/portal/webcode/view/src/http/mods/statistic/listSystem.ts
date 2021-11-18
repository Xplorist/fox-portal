/**
 * @desc 【01】查询系统list
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {}

export const init = new defs.ResultDTO();

export function request(params: Params, options?: any) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/listSystem', params, 'GET'),
    {
      method: 'GET',

      ...options,
    },
  );
}

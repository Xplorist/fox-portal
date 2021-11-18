/**
 * @desc 【0x】模板
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {
  /** param */
  param?: string;
}

export const init = new defs.ResultDTO();

export function request(params: Params, options?: any) {
  return PontCore.fetch(PontCore.getUrl('/statistic/template', params, 'GET'), {
    method: 'GET',

    ...options,
  });
}

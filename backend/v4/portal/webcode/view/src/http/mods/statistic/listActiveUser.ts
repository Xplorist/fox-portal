/**
 * @desc 【05】查询活跃用户list(包含闲置用户list)
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {}

export const init = new defs.ResultDTO();

export function request(
  params: Params,
  body: defs.ListActiveUserQuery,
  options?: any,
) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/listActiveUser', params, 'POST'),
    {
      method: 'POST',

      body,
      ...options,
    },
  );
}

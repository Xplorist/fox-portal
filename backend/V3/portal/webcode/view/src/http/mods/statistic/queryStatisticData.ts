/**
 * @desc 【02】根据条件查询系统统计数据
 */

import * as defs from '../../baseClass';
import { PontCore } from '../../pontCore';

export class Params {}

export const init = new defs.ResultDTO();

export function request(
  params: Params,
  body: defs.StatisticDataQuery,
  options?: any,
) {
  return PontCore.fetch(
    PontCore.getUrl('/statistic/queryStatisticData', params, 'POST'),
    {
      method: 'POST',

      body,
      ...options,
    },
  );
}

import { listSystem } from '@/http/mods/statistic';

/**
 * 獲取系統列表
 */
export const getSystemList = async () => {
  let params = new listSystem.Params();
  let { success, data } = await listSystem.request(params);
  if (success) return data;
  return [];
}

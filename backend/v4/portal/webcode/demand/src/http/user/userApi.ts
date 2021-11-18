import { AxiosRequestConfig } from 'axios';

const baseUri = '/api';

/**
 * @description 獲取用戶信息
 */
export const getUserInfoApi: AxiosRequestConfig = {
  url: baseUri + '/users',
  method: 'GET'
}
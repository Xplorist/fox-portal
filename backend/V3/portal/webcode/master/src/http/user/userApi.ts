import { AxiosRequestConfig } from 'axios';

const baseUri = process.env.NODE_ENV === 'development' ? '/portal' : '';
/**
 * @description 獲取用戶信息
 */
export const getUserInfoApi: AxiosRequestConfig = {
  url:baseUri+'/userInfo/loginUser',
  method: 'GET'
}
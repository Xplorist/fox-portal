import zhTW from 'antd/es/locale/zh_TW';
import { addLocale, setLocale } from 'umi';

import { initAxios } from '@/plugins/axios';
import { UserType } from '@/http/user/userType';
import '@/assets/style.less';
import { getUserInfo } from './http/user/userService';
export interface InitialState {
  user: UserType;
}

const option: any = {
  momentLocale: 'zh-tw',
  antd: zhTW,
};
// export async function getInitialState() {
//   let user = await getUserInfo();
//   return { user };
// }

initAxios();
addLocale('zh-TW', {}, option);
setLocale('zh-TW', false);

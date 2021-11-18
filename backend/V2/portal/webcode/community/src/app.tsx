import zhTW from 'antd/es/locale/zh_TW';
import { addLocale, setLocale } from 'umi';

import { initAxios } from '@/plugins/axios';
import { UserType } from '@/http/user/userType';
import '@/assets/style.less';

export interface InitialState {
  user: UserType;
}

const option: any = {
  momentLocale: 'zh-tw',
  antd: zhTW,
};

initAxios();
addLocale('zh-TW', {}, option);
setLocale('zh-TW', false);

import zhTW from 'antd/es/locale/zh_TW';
import { addLocale, setLocale } from 'umi';

import { initAxios } from '@/plugins/axios';
import '@/assets/style.less';

const option: any = {
  momentLocale: 'zh-tw',
  antd: zhTW,
};

initAxios();
addLocale('zh-TW', {}, option);
setLocale('zh-TW', false);

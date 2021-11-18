import { defineConfig } from 'umi';

// 項目根路徑
const baseURI = process.env.NODE_ENV === 'development' ? '' : '/portal';
// 子項目路徑
const appsHost = '/portal';

const mainHome = '/main';

/**
 * 子項目配置
 */
const apps = [
  {
    name: 'portal-statistics',
    entry: appsHost + mainHome + '/statistics/index.html',
    base: baseURI + mainHome + '/statistics',
  },
  {
    name: 'portal-view',
    entry: appsHost + mainHome + '/view/index.html',
    base: baseURI + mainHome + '/view',
  },
  {
    name: 'portal-demand',
    entry: appsHost + mainHome + '/demand/index.html',
    base: baseURI + mainHome + '/demand',
  },
  {
    name: 'portal-community',
    entry: appsHost + mainHome + '/community/index.html',
    base: baseURI + mainHome + '/community',
  },
];

const config: any = {
  base: baseURI || '/',
  publicPath: `${baseURI}/master/`,
  copy: ['static'],
  request: {
    dataField: 'data',
  },
  favicon: '/favicon.ico',
  cssLoader: {
    localsConvention: 'camelCase',
  },
  locale: {
    antd: true,
    baseNavigator: false,
  },
  antd: {},
  routes: [
    { path: '/', exact: true, redirect: mainHome + '/view/home' },
    { path: mainHome + '/*', component: '@/pages/home/mainAppPage' },
    { path: '*', component: '@/pages/error/page404' },
  ],
  define: {
    // axios baseURI
    baseURI: `${baseURI}/`,
    // 開發環境 axios 默認地址
    apiBase: `http://127.0.0.1:8099${baseURI}/`,
    // moment 日期格式
    dateFormat: 'YYYY-MM-DD',
    timeFormat: 'hh:mm:ss',
    datetimeFormat: 'YYYY-MM-DD hh:mm:ss',
    mainHome
  },
  terserOptions: {
    compress: {
      drop_console: true,
      drop_debugger: true
    },
  },
  headScripts: [
    { src: baseURI + '/master/error.js' }
  ],
  scripts: [
    { src: baseURI + '/master/loading.js' }
  ]
};

if (process.env.NODE_ENV !== 'development') {
  config.qiankun = {
    master: { apps, jsSandbox: true },
  };
}

export default config;

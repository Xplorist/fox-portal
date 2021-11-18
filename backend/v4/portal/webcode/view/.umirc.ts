import { defineConfig } from 'umi';

// 項目根路徑
const baseURI = process.env.NODE_ENV === 'development' ? '' : '/portal/main/view';

export default defineConfig({
  base: baseURI || '/',
  publicPath: `${baseURI}/`,
  copy: ['static'],
  request: {
    dataField: 'data',
  },
  outputPath: '../../src/main/resources/static/main/view',
  favicon: '/favicon.ico',
  locale: {
    antd: true,
    baseNavigator: false,
  },
  antd: {},
  cssLoader: {
    localsConvention: 'camelCase',
  },
  routes: [
    { path: '/', exact: true, redirect: '/home' },
    { path: '/home', component: '@/pages/home/homePage' },
  ],
  define: {
    // axios baseURI
    baseURI: `/portal/`,
    // 開發環境 axios 默認地址
    apiBase: `http://127.0.0.1:8099/portal/`,
    // moment 日期格式
    dateFormat: 'YYYY-MM-DD',
    timeFormat: 'hh:mm:ss',
    datetimeFormat: 'YYYY-MM-DD hh:mm:ss',
  },
  terserOptions: {
    compress: {
      drop_console: true,
    },
  },
  dynamicImport: {
    loading: '@/pages/loading',
  }
});

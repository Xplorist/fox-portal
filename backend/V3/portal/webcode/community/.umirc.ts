import { defineConfig } from 'umi';

// 項目根路徑
const baseURI = process.env.NODE_ENV === 'development' ? '' : '/portal/main/community';

export default defineConfig({
  base: baseURI || '/',
  publicPath: `${baseURI}/`,
  copy: ['static'],
  request: {
    dataField: 'data',
  },
  outputPath: '../../src/main/resources/static/main/community',
  favicon: '/favicon.ico',
  locale: {
    antd: true,
    baseNavigator: false,
  },
  antd: {},
  polyfill: {
    imports: ['core-js/stable'],
  },
  targets: {
    chrome: 49,
  },
  cssLoader: {
    localsConvention: 'camelCase',
  },
  routes: [
    { path: '/', exact: true, redirect: '/home' },
    { path: '/home', component: '@/pages/homePage' },
    { path: '/article', component: '@/pages/articlePage' },
    { path: '/detail', component: '@/pages/detailPage' },
  ],
  define: {
    // axios baseURI
    baseURI: `${baseURI}/`,
    // moment 日期格式
    dateFormat: 'YYYY-MM-DD',
    timeFormat: 'hh:mm:ss',
    datetimeFormat: 'YYYY-MM-DD hh:mm:ss',
  },
  dynamicImport: {
    loading: '@/pages/loading',
  },
  terserOptions: {
    compress: {
      drop_console: true,
    },
  },
});

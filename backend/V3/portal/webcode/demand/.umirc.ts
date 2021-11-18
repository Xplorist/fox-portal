import { defineConfig } from 'umi';

// 項目根路徑
const baseURI = process.env.NODE_ENV === 'development' ? '' : '/portal/main/demand';

export default defineConfig({
  base: baseURI || '/',
  publicPath: `${baseURI}/`,
  copy: ['static'],
  request: {
    dataField: 'data',
  },
  outputPath: '../../src/main/resources/static/main/demand',
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
    { path: '/home', component: '@/pages/homePage' },
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
  }
});

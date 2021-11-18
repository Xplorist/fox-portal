const path = require('path');
const { name } = require('./package');

function resolve(dir) {
  return path.join(__dirname, dir);
}

const port = 8080;

module.exports = {
  outputDir: '../../src/main/resources/static/main/statistics',
  publicPath: '/portal/main/statistics',
  filenameHashing: true,
  css: {
    extract: false
  },
  devServer: {
    // host: '0.0.0.0',
    hot: true,
    disableHostCheck: true,
    port,
    overlay: {
      warnings: false,
      errors: true,
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
  //   proxy: {
  //     '/portal/statistic': {
  //     target: 'http://10.244.231.103:8080/portal/statistic',
  //       changeOrigin: true,
  //       ws: true,
  //       pathRewrite: {
  //         '^/portal/statistic': ''
  //       }
  //     }
  // }
  },
  configureWebpack: {
    resolve: {
      alias: {
        '@': resolve('src'),
      },
    },
    output: {
      library: `${name}-[name]`,
      libraryTarget: 'umd',
      jsonpFunction: `webpackJsonp_${name}`,
    },
  },
};
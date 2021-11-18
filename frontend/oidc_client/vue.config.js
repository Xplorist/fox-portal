module.exports = {
    // 部署应用包时的基本 URL
    publicPath: process.env.NODE_ENV === 'production'? '/portal/': '/portal',
    devServer: {
      proxy: {
          // '/api': {
          //     target: 'http://10.244.186.86:8080/api',
          //     changeOrigin: true,
          //     ws: true,
          //     pathRewrite: {
          //       '^/api': ''
          //     }
          // },
          '/portal/userInfo': {
            target: 'http://localhost:8081/portal/userInfo',
            changeOrigin: true,
            ws: true,
            pathRewrite: {
              '^/portal/userInfo': ''
            }
          }
      }
  }
  };
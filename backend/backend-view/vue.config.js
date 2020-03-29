module.exports = {
  lintOnSave: false,
  devServer: {
    port: 7000,
    // 设置代理
    proxy: {
      '/backend': {
        // 目标 API 地址
        target: 'http://127.0.0.1:8888/',
        // target: 'http://139.196.86.116:8002/',
        // 如果要代理 websockets
        ws: false,
        // 将主机标头的原点更改为目标URL
        changeOrigin: true
      },
    }
  },
  productionSourceMap: true,
  runtimeCompiler: true,
  pages: {
    index: {
      // page 的入口
      entry: 'src/main.js',
      // 模板来源
      template: 'public/index.html',
      // 在 dist/index.html 的输出
      filename: 'index.html'
    }
  },
  //transpileDependencies:["xlsx"]
};

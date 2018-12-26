# 使用方法
- 安装yarn，如果没有安装过，请先安装yarn `npm install -g yarn `
- 设置npm 代理，提升安装速度 `npm config set registry https://registry.npm.taobao.org/`
- 使用 `yarn install ` 命令安装所有依赖文件
- 本地执行`yarn run dev` 启动
# 编译打包
- 本地执行`yarn run build`
- 可以在webpack配置文件中将打包的文件直接放到java工程当中
# 配置修改
- 本地代理
  - 修改配置文件eoraptor-provider/eoraptor-cpic-runtime/cpic-web/web-view/config/index.js当中的proxyTable
  ```angular2html
  proxyTable: {
      //本地代理
      '/platform/*': {
        target: 'http://localhost:8000',
        debug:true,
        changeOrigin: true,
        secure: false
      }
    },
```

// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/index'
import ueboot from 'ueboot'
import iView from 'iView'

/* ueboot封装过的基于iview的自定义样式，也可以引入自己的自定义样式 */
import 'ueboot/dist/styles/ueboot.css'
import 'font-awesome/less/font-awesome.less'

Vue.use(ueboot)
Vue.use(iView)

/* 设置登录后的主界面相关配置 */
ueboot.Config.setConfig({
  logoImage: 'static/image/ueboot.png',
  sysTitle: '后台管理系统',
  page_login: {
    theme: 'theme2',
    // 登录成功后的跳转路径
    successRouter: {path: '/ueboot/shiro/User'}
  },
  page_main: {
    logoStyle: {
      width: '300px'
    },
    menuWidth: 250,
    logoutSuccessRouter: {name: 'login'}
  },
  axios: {baseURL: process.env.CONTEXT, unauthorizedUrl: process.env.CONTEXT_HTML + '/#/login'}
})
Vue.config.productionTip = false

const app = new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
console.log(app !== null)

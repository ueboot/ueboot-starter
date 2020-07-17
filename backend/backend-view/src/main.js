import Vue from 'vue'
import App from './App'
import router from './router/index'
import ueboot from 'ueboot'

/* ueboot封装过的基于iview的自定义样式，也可以引入自己的自定义样式 */
import 'ueboot/lib/ueboot.css'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
Vue.use(ViewUI);
Vue.use(ueboot);

/* 设置登录后的主界面相关配置 */
ueboot.Config.setConfig({
  logoImage: 'public/image/ueboot.png',
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
  axios: {baseURL: '/backend', unauthorizedUrl: process.env.CONTEXT_HTML + '/#/login'}
});
Vue.config.productionTip = false;

const app = new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});
console.log(app !== null);

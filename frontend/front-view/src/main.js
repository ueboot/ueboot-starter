import Vue from 'vue'
import App from './App'
import router from './router/index'

Vue.config.productionTip = false

const app = new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
export default app

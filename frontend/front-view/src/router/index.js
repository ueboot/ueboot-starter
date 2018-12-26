import Vue from 'vue'
import Router from 'vue-router'

import HelloWorld from '../views/HelloWorld'

Vue.use(Router)

// 定义页面路由
export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})

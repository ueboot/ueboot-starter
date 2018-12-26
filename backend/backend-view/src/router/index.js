import Vue from 'vue'
import Router from 'vue-router'

// ueboot提供的权限管理UI界面
import {PageLogin, PageMain, PageShiroUser, PageShiroRole, PageShiroResources} from 'ueboot'

import HelloWorld from '../views/HelloWorld'

Vue.use(Router)

/**
 * 登录路由
 */
const LoginRouter = {
  path: '/login',
  name: 'login',
  component: PageLogin
}

/**
 * 自定义业务页面
 */
const CustomerRouter = {
  path: '/helloWorld',
  name: 'HelloWorld',
  component: HelloWorld
}

/**
 * 权限管理
 */
const UebootShiroRouter = {
  path: '/',
  component: PageMain,
  children: [
    {
      path: '/ueboot/shiro/User',
      name: 'User',
      component: PageShiroUser
    },
    {
      path: '/ueboot/shiro/Role',
      name: 'Role',
      component: PageShiroRole
    },
    {
      path: '/ueboot/shiro/Resources',
      name: 'Resources',
      component: PageShiroResources
    }
  ]
}

export default new Router({
  routes: [
    // 登录路由
    LoginRouter,
    // 权限相关路由
    UebootShiroRouter,
    CustomerRouter
  ]
})

/*
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-27 14:25:02
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 22:34:49
 */
import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
//组件模块
import Home from './views/home.vue'
import Index from './views/index.vue'
import Excel from './views/websocket.vue'
Vue.use(Router)
// 公共路由
export const constantRoutes = [
  { path: '/', redirect: '/index' },
  { path: '/home', name: 'home', component: Home },
  { path: '/index', name: 'index', component: Index },
  { path: '/excel/:id', name: 'excel', component: Excel, props: true },
]

export default new Router({
  base: "/sheel",
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
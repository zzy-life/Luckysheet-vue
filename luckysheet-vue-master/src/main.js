/*
 * @Author: zzy 790002517@qq.com
 * @Date: 2022-06-24 18:33:54
 * @LastEditors: zzy 790002517@qq.com
 * @LastEditTime: 2022-06-27 16:12:08
 */
import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from './router'

Vue.config.productionTip = false

Vue.prototype.$axios=axios
//上传图片路径
Vue.prototype.VueApiUrl = '/api'
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
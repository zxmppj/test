import 'es6-promise/auto'
import Vue from 'vue'
import App from './App'
import router from './router/index'
import api from './api/index';
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false;
Vue.prototype.$api = api;
Vue.use(ElementUI);

new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
});

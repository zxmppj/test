import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from '../page/index'
import Login from '../frame/index/login'
import content from '../page/content'
import oddsList from '../page/oddsList'

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {path: '/', redirect: '/login'},
    {
      path: '/',
      name: '/',
      meta: {requireAuth: false},
      component: Index,
      children: [
        {
          path: '/login',
          name: 'login',
          meta: {requireAuth: false},
          component: Login
        }
      ]
    },
    {
      path: '/content',
      name: 'content',
      meta: {requireAuth: true,},
      component: content,

    },{
      path: '/oddsList',
      name: 'oddsList',
      meta: {requireAuth: false,},
      component: oddsList,
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (localStorage.getItem('token') !== null || sessionStorage.getItem('token') !== null) {
      next();
    } else {
      next({
        path: 'login',
        query: {redirect: to.name}
      });
    }
  } else {
    if (to.name === 'login' || to.name === '/') {
      if (localStorage.getItem('token') !== null || sessionStorage.getItem('token') !== null) {
        next({path: 'content'});
      } else {
        next();
      }
    } else {
      next();
    }
  }
});

export default router;

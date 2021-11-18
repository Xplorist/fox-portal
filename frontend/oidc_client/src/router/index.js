import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/openid_connect_login',
    name: 'OpenIdConnectLogin',
    component: () => import(/* webpackChunkName: "about" */ '../views/OpenIdConnectLogin.vue')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/authorize',
    name: 'authorize',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    /*
    redirect: 'http://10.244.168.180/openid/authorize?response_type=token%20id_token' +
    '&client_id=MacIPortal' +
    '&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fportal%2Fopenid_connect_login' +
    '&scope=openid%20profile' + 
    '&state=' + Math.round(Math.random()*1e14 + 1e15).toString(16) +
    '&nonce=' + Math.round(Math.random()*1e14 + 1e15).toString(16),
    */
  },
  {
    path: '*',
    redirect: '/',// 重定向规则放在路由表的最底部，当前面所有的路由都没有匹配到时，会进入此重定向规则，重定向到首页
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  let userInfo = sessionStorage.getItem('userInfo');
  let access_token = sessionStorage.getItem('access_token');

  if (to.path === '/authorize' || to.path === '/openid_connect_login') {
    next();
  } else {
    if (userInfo === null || access_token === null || access_token === '') {
      next('/authorize');
    } else {
      next();
    }
  }
});

export default router

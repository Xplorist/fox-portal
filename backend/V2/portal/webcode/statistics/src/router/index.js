import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../components/statistics/Home')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('../components/EchartsNormal')
  },
  {
    path: '/sysinfo',
    name: 'Sysinfo',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Sysinfo.vue')
  }
  // {
  //   path: '/sysinfo/:id',
  //   name: 'Sysinfo:id',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/Sysinfo.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    component: () => import('../components/loginpage.vue')
  },
    {
    path: '/login',
    component: () => import('../components/loginpage.vue')
  },
  {
    path: '/register',
    component: () => import('../components/registerpage.vue')
  },
  {
    path: '/forget',
    component: () => import('../components/forgetpage.vue')
  },
  {
  path: '/custmain',
  component: () => import('../components/custmain.vue')
  },
  {
    path:'/manager',
    component: () => import('../components/manager.vue')
  },
   {
    path:'/my',
    component: () => import('../components/my.vue')
  },
    {
    path:'/mydata',
    component: () => import('../components/mydata.vue')
  },
    {
    path:'/workerpage',
    component: () => import('../components/workerpage.vue')
  },
    {
    path:'/lab',
    component: () => import('../components/lab.vue')
  },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
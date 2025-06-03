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
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
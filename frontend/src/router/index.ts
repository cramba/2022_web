import { createRouter, createWebHistory } from 'vue-router'
import AngeboteView from '../views/AngeboteView.vue'
import GebotView from '../views/GebotView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: AngeboteView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },

    {
      path: '/gebot',
      name: '/:angebotidstr',
      component: GebotView,
      props:true
    }
  ]
})

export default router

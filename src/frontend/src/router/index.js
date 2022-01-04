import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import board from "@/views/board";
import Login from "@/views/Login";
import signup from "@/views/signup";
import mypage from "@/views/mypage/mypage";
import personalInfo from "@/views/mypage/personalInfo";
import mypageNavi from "@/views/mypage/mypageNavi";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
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
    path: '/board',
    name: 'board',
    component: board
  },
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/signup',
    name: 'signup',
    component: signup
  },
  {
    path: '/mypage',
    name: 'mypage',
    component: mypage
  },
  {
    path: '/mypage/personal-info',
    name: 'personalIofo',
    component: personalInfo
  },
  {
    path: '/mypageNavi',
    name: 'mypageNavi',
    component: mypageNavi
  },
]

const router = new VueRouter({
  routes
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import board from "@/views/board/board";
import Login from "@/views/Login";
import signup from "@/views/signup";
import mypage from "@/views/mypage/mypage";
import personalInfo from "@/views/mypage/personalInfo";
import mypageNavi from "@/views/mypage/mypageNavi";
import nickname from "@/views/mypage/infoEdit/nickname";
import name from "@/views/mypage/infoEdit/name"
import boardWrite from "@/views/board/boardWrite";
import boardEdit from "@/views/board/boardEdit";

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
    path: '/board',
    name: 'board',
    component: board
  },
  {
    path: '/boardEdit',
    name: 'boardEdit',
    component: boardEdit
  },
  {
    path: '/boardWrite',
    name: 'boardWrite',
    component: boardWrite
  },

  {
    path: '/mypage',
    name: 'mypage',
    component: mypage
  },
  {
    path: '/mypageNavi',
    name: 'mypageNavi',
    component: mypageNavi
  },
  {
    path: '/mypage/personal-info',
    name: 'personalIofo',
    component: personalInfo,
  },
  {
    path: '/mypage/name',
    name: 'name',
    component: name
  },
  {
    path: '/mypage/nickname',
    name: 'nickname',
    component: nickname
  },
  {
    path: '/mypage/email',
    name: 'email',
    component: personalInfo
  },
]

const router = new VueRouter({
  routes
})

export default router

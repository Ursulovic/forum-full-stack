import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "@/views/Login";
import AddUser from "@/views/AddUser";
import CategoryPanel from "@/views/CategoryPanel";
import AddCategory from "@/views/AddCategory";
import ArticlePanel from "@/views/ArticlePanel";
import AddArticle from "@/views/AddArticle";
import AdminPanel from "@/views/AdminPanel";
import SearchArticle from "@/views/SearchArticle";
import HomePage from "@/views/ReadingPlatform/HomePage";
import MostReaded from "@/views/ReadingPlatform/MostReaded";
import CategoryFilter from "@/views/ReadingPlatform/CategoryFilter";
import SingleArticle from "@/views/ReadingPlatform/SingleArticle";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/article/filter/category',
    name: 'filterCategory',
    component: CategoryFilter
  },
  {
    path: '/user/',
    name: 'user',
    component: AdminPanel
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage,
  },
  {
    path: '/article/MostReaded',
    name: 'Most readed',
    component: MostReaded
  },
  {
    path: '/article/search',
    name: 'SearchArticles',
    component: SearchArticle
  },
  {
    path: '/article/read/:id?',
    name: 'ReadArticle',
    component: SingleArticle
  },
  {
    path: '/user/add',
    name: 'addUser',
    component: AddUser
  },
  {
    path: '/category',
    name: 'category',
    component: CategoryPanel
  },
  {
    path: '/category/add',
    name: 'addCategory',
    component: AddCategory
  },
  {
    path: '/article',
    name: 'article',
    component: ArticlePanel
  },
  {
    path: '/article/add',
    name: 'addArticle',
    component: AddArticle
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router

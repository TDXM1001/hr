import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

/**
 * 定义前端基础路由配置
 */
const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'Login',
    // 采用懒加载方式引入 Login 视图组件 (对应组件暂未实现)
    component: () => import('../views/login/Index.vue')
  }
];

/**
 * 实例化 Vue Router，并使用基于 HTML5 history 的模式
 */
const router = createRouter({
  history: createWebHistory(),
  routes
});

// 导出路由实例，供主应用使用
export default router;

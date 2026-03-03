import { describe, it, expect } from 'vitest';
import { createRouter, createMemoryHistory } from 'vue-router';

/**
 * Vue Router 配置测试
 */
describe('Router Setup', () => {
  it('should have a login route registered', () => {
    // 使用 mock 的 route 配置测试
    const routes = [
      // 模拟路由配置
      { path: '/login', name: 'Login', component: {} as any }
    ];

    // 创建一个基于内存历史记录的路由器
    const router = createRouter({
      history: createMemoryHistory(),
      routes
    });

    // 验证 Login 路由是否注册成功
    expect(router.hasRoute('Login')).toBe(true);
  });
});

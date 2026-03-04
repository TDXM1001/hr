import { describe, it, expect, beforeEach } from 'vitest';
import { setActivePinia, createPinia } from 'pinia';
import { useUserStore } from './user';

/**
 * 用户状态管理Store的测试用例
 */
describe('User Store API', () => {
  beforeEach(() => {
    // 在每个测试开始前初始化Pinia
    setActivePinia(createPinia());
  });

  /**
   * 测试Token的正确设置
   */
  it('should set token correctly', () => {
    const store = useUserStore();
    store.setToken('mock-jwt-token');

    // 验证token是否被成功设置
    expect(store.token).toBe('mock-jwt-token');
  });
});

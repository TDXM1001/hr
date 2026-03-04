import { defineStore } from 'pinia';

/**
 * 用户状态Store定义
 */
export const useUserStore = defineStore('user', {
  state: () => ({
    token: '' // 用户认证Token
  }),
  actions: {
    /**
     * 设置Token
     * @param newToken 新的Token
     */
    setToken(newToken: string) {
      this.token = newToken;
    }
  }
});

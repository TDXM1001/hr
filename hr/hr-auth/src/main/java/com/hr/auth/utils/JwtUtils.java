package com.hr.auth.utils;

/**
 * JWT (JSON Web Token) 工具类
 * 用于生成、解析和验证 Token
 */
public class JwtUtils {
    // 简化 Mock 实现前缀，实际项目中需引入 jjwt 库并使用秘钥进行签名
    private static final String PREFIX = "mocked-jwt-";

    /**
     * 根据用户名生成 JWT Token
     * 
     * @param username 用户名
     * @return 模拟的 JWT 字符串
     */
    public static String generateToken(String username) {
        return PREFIX + username;
    }

    /**
     * 解析 JWT Token，提取用户名
     * 
     * @param token JWT 字符串
     * @return 提取的用户名，如果解析失败则返回 null
     */
    public static String parseUsername(String token) {
        if (token != null && token.startsWith(PREFIX)) {
            return token.substring(PREFIX.length());
        }
        return null; // 非法或为空的 Token 返回 null
    }
}

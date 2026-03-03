package com.hr.auth.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JWT 工具类测试
 */
public class JwtUtilsTest {
    @Test
    public void testGenerateAndParseToken() {
        String username = "admin";
        
        // 测试生成 Token
        String token = JwtUtils.generateToken(username);
        assertNotNull(token, "生成的 Token 不应为空");
        
        // 测试解析 Token
        assertEquals("admin", JwtUtils.parseUsername(token), "解析出的用户名应当与预期相符");
    }
}

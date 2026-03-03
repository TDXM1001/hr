package com.hr.auth.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SysUserTest {
    @Test
    public void testUserEntityCreation() {
        SysUser user = new SysUser();
        user.setUsername("admin");
        user.setDeleted(false);
        
        assertEquals("admin", user.getUsername());
        assertFalse(user.getDeleted());
    }
}

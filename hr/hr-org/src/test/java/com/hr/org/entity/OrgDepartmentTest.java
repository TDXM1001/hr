package com.hr.org.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * OrgDepartment 组织部门实体类的测试类
 */
public class OrgDepartmentTest {
    
    @Test
    public void testDepartmentEntityCreation() {
        // 创建一个部门实例并设置属性
        OrgDepartment dept = new OrgDepartment();
        dept.setDeptName("研发部");
        dept.setParentId(0L);
        dept.setDeleted(false);
        
        // 验证属性是否设置成功
        assertEquals("研发部", dept.getDeptName(), "部门名称应当正确");
        assertEquals(0L, dept.getParentId(), "父级ID应当为0");
        assertFalse(dept.getDeleted(), "逻辑删除标志应当为 false");
    }
}

package com.hr.employee.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 员工实体相关测试
 */
public class EmpEmployeeTest {
    
    /**
     * 测试员工实体对象的基本创建与属性设置
     */
    @Test
    public void testEmployeeEntityCreation() {
        EmpEmployee emp = new EmpEmployee();
        
        // 设置员工基本信息
        emp.setName("张三");
        emp.setPhone("13800138000");
        emp.setCompanyId(10L);
        
        // 验证设置是否成功
        assertEquals("张三", emp.getName(), "员工姓名应一致");
        assertEquals("13800138000", emp.getPhone(), "员工手机号码应一致");
        assertEquals(10L, emp.getCompanyId(), "员工公司ID应一致");
    }
}

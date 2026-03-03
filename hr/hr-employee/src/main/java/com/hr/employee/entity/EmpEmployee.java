package com.hr.employee.entity;

/**
 * 员工基本信息实体类
 */
public class EmpEmployee {
    
    /**
     * 员工姓名
     */
    private String name;
    
    /**
     * 员工手机号码
     */
    private String phone;
    
    /**
     * 所属公司ID
     */
    private Long companyId;

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) { 
        this.name = name; 
    }

    public String getPhone() { 
        return phone; 
    }
    
    public void setPhone(String phone) { 
        this.phone = phone; 
    }

    public Long getCompanyId() { 
        return companyId; 
    }
    
    public void setCompanyId(Long companyId) { 
        this.companyId = companyId; 
    }
}

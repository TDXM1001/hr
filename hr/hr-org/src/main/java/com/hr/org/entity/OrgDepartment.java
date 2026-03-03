package com.hr.org.entity;

/**
 * 组织部门实体类
 * 对应数据库中的 org_department 表
 */
public class OrgDepartment {
    /**
     * 部门名称
     */
    private String deptName;
    
    /**
     * 父级部门 ID
     */
    private Long parentId;
    
    /**
     * 逻辑删除标志
     */
    private Boolean deleted;

    // Getter & Setter 方法
    
    public String getDeptName() { 
        return deptName; 
    }
    
    public void setDeptName(String deptName) { 
        this.deptName = deptName; 
    }

    public Long getParentId() { 
        return parentId; 
    }
    
    public void setParentId(Long parentId) { 
        this.parentId = parentId; 
    }

    public Boolean getDeleted() { 
        return deleted; 
    }
    
    public void setDeleted(Boolean deleted) { 
        this.deleted = deleted; 
    }
}

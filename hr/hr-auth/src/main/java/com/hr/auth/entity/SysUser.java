package com.hr.auth.entity;

public class SysUser {
    private String username;
    private Boolean deleted;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}

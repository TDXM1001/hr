# 人事管理系统第三期 (HR System MVP Phase 3) Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 初始化 `hr-employee` 员工模块基础原型，在 `hr-common` 增加业务异常类基础，并在前端通过 Pinia 配置状态管理原型。

**Architecture:** `hr-employee` 服务作为员工生命周期管理的核心，提供基本人员建档模型；`hr-common` 提供基础的统一异常类给各服务使用；前端引入 Pinia 状态工具，处理认证 Token 的全局状态。

**Tech Stack:** Java 17, Spring Boot 3.2, Maven, JUnit 5, Vue 3, Pinia, Vitest

---

### Task 1: 初始化 hr-employee 模块及员工实体

**Files:**
- Create: `hr-employee/pom.xml`
- Create: `hr-employee/src/main/java/com/hr/employee/entity/EmpEmployee.java`
- Test: `hr-employee/src/test/java/com/hr/employee/entity/EmpEmployeeTest.java`

**Step 1: Write the failing test**

```java
package com.hr.employee.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpEmployeeTest {
    @Test
    public void testEmployeeEntityCreation() {
        EmpEmployee emp = new EmpEmployee();
        emp.setName("张三");
        emp.setPhone("13800138000");
        emp.setCompanyId(10L);
        
        assertEquals("张三", emp.getName());
        assertEquals("13800138000", emp.getPhone());
        assertEquals(10L, emp.getCompanyId());
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl hr-employee -Dtest=EmpEmployeeTest`
Expected: FAIL 目标模块缺失或 "Cannot resolve symbol EmpEmployee"

**Step 3: Write minimal implementation**

先在 `hr-employee/pom.xml` 创建模块配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.hr</groupId>
        <artifactId>hr-system</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <artifactId>hr-employee</artifactId>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```
（注意同时在根目录的 `pom.xml` 的 `<modules>` 列表中注册 `<module>hr-employee</module>`）

接着创建 `hr-employee/src/main/java/com/hr/employee/entity/EmpEmployee.java` 实体类：
```java
package com.hr.employee.entity;

public class EmpEmployee {
    private String name;
    private String phone;
    private Long companyId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl hr-employee -Dtest=EmpEmployeeTest`
Expected: PASS

**Step 5: Commit**

```bash
git add pom.xml hr-employee/
git commit -m "feat(employee): init hr-employee module and EmpEmployee entity"
```

### Task 2: 添加业务异常类 BusinessException (hr-common)

**Files:**
- Create: `hr-common/src/main/java/com/hr/common/exception/BusinessException.java`
- Test: `hr-common/src/test/java/com/hr/common/exception/BusinessExceptionTest.java`

**Step 1: Write the failing test**

```java
package com.hr.common.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessExceptionTest {
    @Test
    public void testBusinessExceptionCreation() {
        BusinessException ex = new BusinessException(400, "参数错误");
        assertEquals(400, ex.getCode());
        assertEquals("参数错误", ex.getMessage());
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl hr-common -Dtest=BusinessExceptionTest`
Expected: FAIL 错误 "Cannot resolve symbol BusinessException"

**Step 3: Write minimal implementation**

```java
// hr-common/src/main/java/com/hr/common/exception/BusinessException.java
package com.hr.common.exception;

public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl hr-common -Dtest=BusinessExceptionTest`
Expected: PASS

**Step 5: Commit**

```bash
git add hr-common/
git commit -m "feat(common): add BusinessException class"
```

### Task 3: 前端状态管理基础配置 (Pinia)

**Files:**
- Create: `hr-frontend/src/store/user.ts`
- Test: `hr-frontend/src/store/user.spec.ts`

**Step 1: Write the failing test**

```typescript
// hr-frontend/src/store/user.spec.ts
import { describe, it, expect, beforeEach } from 'vitest';
import { setActivePinia, createPinia } from 'pinia';
import { useUserStore } from './user';

describe('User Store API', () => {
    beforeEach(() => {
        setActivePinia(createPinia());
    });

    it('should set token correctly', () => {
        const store = useUserStore();
        store.setToken('mock-jwt-token');
        expect(store.token).toBe('mock-jwt-token');
    });
});
```

**Step 2: Run test to verify it fails**

Run: `cd hr-frontend && npm run test`
Expected: FAIL 错误 "Cannot find module 'pinia'" 或者找不到 `user` 模块资源

**Step 3: Write minimal implementation**

```typescript
// hr-frontend/src/store/user.ts
import { defineStore } from 'pinia';

export const useUserStore = defineStore('user', {
    state: () => ({
        token: ''
    }),
    actions: {
        setToken(newToken: string) {
            this.token = newToken;
        }
    }
});
```

**Step 4: Run test to verify it passes**

Run: `cd hr-frontend && npm install pinia@2 && npx vitest run src/store/user.spec.ts`
Expected: PASS

**Step 5: Commit**

```bash
git add hr-frontend/
git commit -m "feat(frontend): setup pinia and basic user token store"
```

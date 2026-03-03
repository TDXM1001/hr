# 人事管理系统第一期 (HR System MVP Phase 1) Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 搭建系统基础脚手架，包含公共模块(hr-common)、认证服务(hr-auth)基础原型以及前端基础项目初始化，打通前后端骨架。

**Architecture:** 后端采用多模块 Maven 项目，拆分为 hr-common, hr-auth, hr-gateway 等。Spring Boot 3.x 独立服务，使用统一的 API Result 对象。前端使用 Vue 3 + Vite 搭建工程体系。

**Tech Stack:** Java 17, Spring Boot 3.2, Maven, JUnit 5, Vue 3, Vite, Vitest

---

### Task 1: 初始化项目根结构与 API 统一返回对象 (hr-common)

**Files:**
- Create: `pom.xml` (根目录)
- Create: `hr-common/pom.xml`
- Create: `hr-common/src/main/java/com/hr/common/api/Result.java`
- Test: `hr-common/src/test/java/com/hr/common/api/ResultTest.java`

**Step 1: Write the failing test**

```java
package com.hr.common.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {
    @Test
    public void testSuccessResponseFormat() {
        Result<String> result = Result.success("ok");
        assertEquals(200, result.getCode());
        assertEquals("操作成功", result.getMessage());
        assertEquals("ok", result.getData());
        assertTrue(result.getTimestamp() > 0);
    }
}
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl hr-common -Dtest=ResultTest`
Expected: FAIL 错误 "Cannot resolve symbol Result"

**Step 3: Write minimal implementation**

首先在根目录创建基础 `pom.xml` 和 `hr-common` 模块结构。
```java
// hr-common/src/main/java/com/hr/common/api/Result.java
package com.hr.common.api;

public class Result<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;

    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.code = 200;
        r.message = "操作成功";
        r.data = data;
        r.timestamp = System.currentTimeMillis();
        return r;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public T getData() { return data; }
    public long getTimestamp() { return timestamp; }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl hr-common -Dtest=ResultTest`
Expected: PASS

**Step 5: Commit**

```bash
git add pom.xml hr-common/
git commit -m "feat: init parent pom and hr-common Result class"
```

### Task 2: 搭建 hr-auth 服务基础与 SysUser 实体

**Files:**
- Create: `hr-auth/pom.xml`
- Create: `hr-auth/src/main/java/com/hr/auth/entity/SysUser.java`
- Test: `hr-auth/src/test/java/com/hr/auth/entity/SysUserTest.java`

**Step 1: Write the failing test**

```java
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
```

**Step 2: Run test to verify it fails**

Run: `mvn test -pl hr-auth -Dtest=SysUserTest`
Expected: FAIL 错误 "Cannot resolve symbol SysUser"

**Step 3: Write minimal implementation**

```java
// hr-auth/src/main/java/com/hr/auth/entity/SysUser.java
package com.hr.auth.entity;

public class SysUser {
    private String username;
    private Boolean deleted;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }
}
```

**Step 4: Run test to verify it passes**

Run: `mvn test -pl hr-auth -Dtest=SysUserTest`
Expected: PASS

**Step 5: Commit**

```bash
git add hr-auth/
git commit -m "feat: init hr-auth module and SysUser entity"
```

### Task 3: 前端主应用工程初始化 (Vue 3 + Vite)

**Files:**
- Create: `hr-frontend/package.json`
- Create: `hr-frontend/src/utils/request.ts`
- Test: `hr-frontend/src/utils/request.spec.ts`

**Step 1: Write the failing test**

```typescript
// hr-frontend/src/utils/request.spec.ts
import { describe, it, expect } from 'vitest';
import { formatResponse } from './request';

describe('Request Utility', () => {
    it('should format API response correctly', () => {
        const rawResponse = { data: { code: 200, message: '操作成功', data: 'ok' } };
        const result = formatResponse(rawResponse);
        expect(result).toBe('ok');
    });
});
```

**Step 2: Run test to verify it fails**

Run: `cd hr-frontend && npm run test`
Expected: FAIL 错误 "Cannot find module './request'"

**Step 3: Write minimal implementation**

```typescript
// hr-frontend/src/utils/request.ts
export function formatResponse(response: any) {
    if (response.data && response.data.code === 200) {
        return response.data.data;
    }
    throw new Error(response.data?.message || '请求失败');
}
```

**Step 4: Run test to verify it passes**

Run: `cd hr-frontend && npx vitest run`
Expected: PASS

**Step 5: Commit**

```bash
git add hr-frontend/
git commit -m "feat: init frontend workspace and request util"
```

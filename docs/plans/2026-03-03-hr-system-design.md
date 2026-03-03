# 人事管理系统（HR System）架构与设计文档

## 概述
本项目旨在为大型企业/集团化（1000+人）构建一套成熟的企业级人事管理系统。第一期 MVP 包含四大核心模块：员工信息管理、组织架构管理、考勤与假期管理、离职与异动管理，构成员工全生命周期的基础闭环。系统采用前后端分离的微服务架构（精简拆分），并支持基于角色的权限控制和层级数据隔离。

## 一、 系统架构

系统设计为 7 个独立 Spring Boot 服务（无 Spring Cloud），使用 Nginx 作为 API 网关。

- **前端**: Vue 3 + Vite + Element Plus + Pinia + Vue Router 4
- **后端**: Spring Boot 3.x
- **网关**: Nginx (路由分发、负载均衡)
- **服务间通信**: OpenFeign (独立使用)
- **数据库**: MySQL 8.0 (按服务分库，主从分离)
- **基础设施**: Redis (Token/缓存), RabbitMQ (异步事件), MinIO (文件存储)

### 服务拆分与端口
1. `hr-gateway` (Nginx) - API网关、静态资源代理
2. `hr-auth` (8081) - 认证授权服务（登录、权限、Token）
3. `hr-org` (8082) - 组织架构服务（部门、岗位、架构图）
4. `hr-employee` (8083) - 员工管理服务（档案、合规信息维护）
5. `hr-attendance` (8084) - 考勤假期服务（打卡、排班、请假）
6. `hr-transfer` (8085) - 异动离职服务（调岗、调薪、离职、交接）
7. `hr-common` (8086) - 公共服务（字典、文件存储、消息通知）

### 数据权限模型 (RBAC + 数据权限)
通过 `data_scope` 字段实现：
- `ALL` — 全部数据 (超级管理员 / HR 总监)
- `COMPANY` — 本公司数据 (分公司 HR)
- `DEPARTMENT` — 本部门及下级部门数据 (部门经理)
- `SELF` — 仅个人数据 (普通员工)

## 二、 数据库设计

每个服务拥有独立的数据库。所有表均包含 `create_time`, `update_time` 和 `deleted`（逻辑删除）字段。敏感数据（如身份证）加密存储。

### 1. hr_auth
- `sys_user`: 系统账号密码，关联员工
- `sys_role`: 角色定义及 `data_scope`
- `sys_user_role`: 用户角色中间表
- `sys_menu`: 目录、菜单、按钮权限
- `sys_role_menu`: 角色权限中间表

### 2. hr_org
- `org_company`: 公司/集团结构
- `org_department`: 部门层级结构
- `org_position`: 岗位及编制信息
- `org_change_log`: 组织架构调整审查日志

### 3. hr_employee
- `emp_employee`: 员工基础信息
- `emp_contract`: 员工合同记录
- `emp_certificate`: 证件信息
- `emp_emergency_contact`: 紧急联系人
- `emp_change_log`: 核心字段变更审计日志

### 4. hr_attendance
- `att_shift`: 班次定义
- `att_schedule`: 员工排班信息
- `att_clock_record`: 日常打卡记录
- `att_leave_type`: 假期类型及规则
- `att_leave_balance`: 员工假期额度
- `att_leave_request`: 请假申请单
- `att_monthly_summary`: 月度考勤统计

### 5. hr_transfer
- `trf_transfer_request`: 异动/调岗申请
- `trf_resignation`: 离职申请
- `trf_handover`: 离职交接项
- `trf_exit_interview`: 离职面谈记录
- `trf_former_employee`: 离职人员库

### 6. hr_common
- `com_dict_type` / `com_dict_data`: 系统数据字典
- `com_file`: MinIO文件元数据
- `com_operation_log`: 全局操作日志审计
- `com_notification`: 站内信/审批提醒

## 三、 API 接口设计

遵循 RESTful 风格，内部服务间调用路径统一定义在 `/api/*/internal/**`，对外部暴露的接口由各自的前缀 `/api/auth/`, `/api/org/`, `/api/emp/` 等路由控制。
接口统一返回格式：
```json
{
  "code": 200,
  "message": "操作成功",
  "data": { ... },
  "timestamp": 1709481600000
}
```

## 四、 前端设计

采用经典的后台管理系统布局：侧边栏菜单 + 顶部导航 + 面包屑 + 多标签页(TabsView)。

### 核心页面模块
1. **工作台 Dashboard**: 数据概览（入职/离职统计）、待办事项、系统通知等汇总展示。
2. **员工管理**: 员工花名册（高级搜索、导入导出）、分步新增表单、员工详情卡片视图。
3. **组织架构**: 树形部门公司结构、图表级组织架构图、岗位编制视图。
4. **考勤管理**: 动态排班日历组件、考勤异常高亮表格、请假统计面板。
5. **异动管理**: 全周期表单与审批状态追踪，前员工库归档展示。
6. **系统管理**: 用户、角色、权限树、字典配置。

### 核心技术点
- `v-permission` 细粒度按钮级指令控权。
- 采用组合式函数 (Composables) 提取 `usePermission`, `usePagination`, `useDict` 逻辑复用。
- 复杂的业务组件封装（如 `TreeSelect` 树形下拉选择、`OrgChart` 组织可视化）。

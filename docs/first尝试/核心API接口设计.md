🔌 核心 API 接口设计
一、hr-auth 认证授权服务
方法	路径	说明	权限
认证			
POST	/api/auth/login	用户登录，返回 JWT Token	公开
POST	/api/auth/logout	退出登录，Token 加入黑名单	已登录
POST	/api/auth/refresh	刷新 Token	已登录
GET	/api/auth/userinfo	获取当前用户信息 + 权限 + 菜单	已登录
用户管理			
GET	/api/auth/users	分页查询系统用户	user:list
POST	/api/auth/users	新增用户（绑定员工）	user:add
PUT	/api/auth/users/{id}	编辑用户	user:edit
DELETE	/api/auth/users/{id}	删除用户	user:delete
PUT	/api/auth/users/{id}/reset-password	重置密码	user:reset-pwd
PUT	/api/auth/users/{id}/status	启用/禁用用户	user:edit
角色管理			
GET	/api/auth/roles	角色列表	role:list
POST	/api/auth/roles	新增角色	role:add
PUT	/api/auth/roles/{id}	编辑角色（含数据权限配置）	role:edit
DELETE	/api/auth/roles/{id}	删除角色	role:delete
PUT	/api/auth/roles/{id}/menus	分配菜单权限	role:assign
菜单管理			
GET	/api/auth/menus/tree	菜单树	menu:list
POST	/api/auth/menus	新增菜单	menu:add
PUT	/api/auth/menus/{id}	编辑菜单	menu:edit
DELETE	/api/auth/menus/{id}	删除菜单	menu:delete
二、hr-org 组织架构服务
方法	路径	说明	权限
公司管理			
GET	/api/org/companies/tree	公司树形结构	company:list
POST	/api/org/companies	新增公司/分公司	company:add
PUT	/api/org/companies/{id}	编辑公司信息	company:edit
DELETE	/api/org/companies/{id}	删除公司	company:delete
部门管理			
GET	/api/org/departments/tree	部门树形结构（按公司筛选）	dept:list
GET	/api/org/departments/{id}	部门详情	dept:list
POST	/api/org/departments	新增部门	dept:add
PUT	/api/org/departments/{id}	编辑部门	dept:edit
DELETE	/api/org/departments/{id}	删除/撤销部门	dept:delete
POST	/api/org/departments/merge	合并部门	dept:edit
岗位管理			
GET	/api/org/positions	岗位列表（分页，按部门筛选）	position:list
POST	/api/org/positions	新增岗位	position:add
PUT	/api/org/positions/{id}	编辑岗位	position:edit
DELETE	/api/org/positions/{id}	删除岗位	position:delete
架构图			
GET	/api/org/chart	组织架构图数据（树形 JSON）	org:chart
GET	/api/org/chart/export	导出架构图	org:chart
变更记录			
GET	/api/org/change-logs	架构调整记录（分页）	org:log
内部 Feign 接口			
GET	/api/org/internal/departments/{id}	查部门名称（内部调用）	内部
GET	/api/org/internal/positions/{id}	查岗位信息（内部调用）	内部
GET	/api/org/internal/departments/{id}/employees	查部门下员工 ID 列表	内部
三、hr-employee 员工管理服务
方法	路径	说明	权限
员工档案			
GET	/api/emp/employees	分页查询员工（支持多条件筛选）	emp:list
GET	/api/emp/employees/{id}	员工详情（含合同/证件/紧急联系人）	emp:detail
POST	/api/emp/employees	新增员工	emp:add
PUT	/api/emp/employees/{id}	编辑员工基础信息	emp:edit
DELETE	/api/emp/employees/{id}	删除员工（逻辑删除）	emp:delete
POST	/api/emp/employees/import	批量导入（Excel）	emp:import
GET	/api/emp/employees/export	批量导出（Excel）	emp:export
合同管理			
GET	/api/emp/employees/{empId}/contracts	员工合同列表	contract:list
POST	/api/emp/employees/{empId}/contracts	新增合同	contract:add
PUT	/api/emp/contracts/{id}	编辑合同	contract:edit
GET	/api/emp/contracts/expiring	即将到期合同提醒	contract:list
证件管理			
GET	/api/emp/employees/{empId}/certificates	员工证件列表	cert:list
POST	/api/emp/employees/{empId}/certificates	新增证件	cert:add
PUT	/api/emp/certificates/{id}	编辑证件	cert:edit
DELETE	/api/emp/certificates/{id}	删除证件	cert:delete
紧急联系人			
GET	/api/emp/employees/{empId}/emergency-contacts	紧急联系人列表	emp:detail
POST	/api/emp/employees/{empId}/emergency-contacts	新增联系人	emp:edit
变更记录			
GET	/api/emp/employees/{empId}/change-logs	员工信息变更记录	emp:log
内部 Feign 接口			
GET	/api/emp/internal/employees/{id}	查员工基础信息（内部调用）	内部
GET	/api/emp/internal/employees/batch	批量查员工（传 IDs）	内部
四、hr-attendance 考勤假期服务
方法	路径	说明	权限
班次管理			
GET	/api/att/shifts	班次列表	shift:list
POST	/api/att/shifts	新增班次	shift:add
PUT	/api/att/shifts/{id}	编辑班次	shift:edit
DELETE	/api/att/shifts/{id}	删除班次	shift:delete
排班管理			
GET	/api/att/schedules	查询排班（按部门/日期范围）	schedule:list
POST	/api/att/schedules/batch	批量排班	schedule:add
PUT	/api/att/schedules/{id}	修改排班	schedule:edit
打卡记录			
POST	/api/att/clock/in	上班打卡	已登录
POST	/api/att/clock/out	下班打卡	已登录
GET	/api/att/clock/today	今日打卡状态	已登录
GET	/api/att/clock/records	打卡记录查询（分页）	clock:list
POST	/api/att/clock/makeup	补卡申请	已登录
假期管理			
GET	/api/att/leave-types	假期类型列表	leave:list
POST	/api/att/leave-types	新增假期类型	leave:add
GET	/api/att/leave/balance	查询假期余额（个人/部门）	已登录
POST	/api/att/leave/requests	提交请假申请	已登录
GET	/api/att/leave/requests	请假申请列表	leave:list
PUT	/api/att/leave/requests/{id}/approve	审批请假	leave:approve
PUT	/api/att/leave/requests/{id}/cancel	取消/销假	已登录
考勤统计			
GET	/api/att/summary/monthly	月度考勤汇总（按部门/个人）	att:report
GET	/api/att/summary/abnormal	异常考勤列表	att:report
GET	/api/att/summary/export	导出考勤报表	att:export
五、hr-transfer 异动离职服务
方法	路径	说明	权限
异动管理			
GET	/api/trf/transfers	异动申请列表（分页）	transfer:list
POST	/api/trf/transfers	提交异动申请	transfer:add
GET	/api/trf/transfers/{id}	异动申请详情	transfer:detail
PUT	/api/trf/transfers/{id}/approve	审批异动	transfer:approve
离职管理			
GET	/api/trf/resignations	离职申请列表	resign:list
POST	/api/trf/resignations	提交离职申请	resign:add
PUT	/api/trf/resignations/{id}/approve	审批离职	resign:approve
工作交接			
GET	/api/trf/resignations/{id}/handovers	交接事项列表	handover:list
POST	/api/trf/resignations/{id}/handovers	添加交接事项	handover:add
PUT	/api/trf/handovers/{id}/complete	确认交接完成	handover:edit
离职面谈			
POST	/api/trf/resignations/{id}/interview	记录离职面谈	interview:add
GET	/api/trf/resignations/{id}/interview	查看面谈记录	interview:list
离职员工库			
GET	/api/trf/former-employees	离职员工库（分页查询）	former:list
GET	/api/trf/former-employees/{id}	离职员工详情	former:detail
PUT	/api/trf/former-employees/{id}/rehire	标记可返聘/不可返聘	former:edit
统计			
GET	/api/trf/statistics/turnover	离职率统计	trf:report
GET	/api/trf/statistics/reasons	离职原因分析	trf:report
六、hr-common 公共服务
方法	路径	说明	权限
数据字典			
GET	/api/com/dicts/types	字典类型列表	dict:list
POST	/api/com/dicts/types	新增字典类型	dict:add
GET	/api/com/dicts/data/{dictType}	按类型查字典数据	已登录
POST	/api/com/dicts/data	新增字典数据	dict:add
文件管理			
POST	/api/com/files/upload	上传文件	已登录
GET	/api/com/files/{id}/download	下载文件	已登录
DELETE	/api/com/files/{id}	删除文件	file:delete
通知消息			
GET	/api/com/notifications	我的消息列表	已登录
GET	/api/com/notifications/unread-count	未读消息数	已登录
PUT	/api/com/notifications/{id}/read	标记已读	已登录
PUT	/api/com/notifications/read-all	全部标记已读	已登录
操作日志			
GET	/api/com/logs/operations	操作日志查询	log:list
七、统一响应格式
json
{
  "code": 200,
  "message": "操作成功",
  "data": { ... },
  "timestamp": 1709481600000
}
分页响应：

json
{
  "code": 200,
  "message": "操作成功",
  "data": {
    "records": [ ... ],
    "total": 150,
    "current": 1,
    "size": 20,
    "pages": 8
  }
}
错误码规范：

错误码	含义
200	成功
400	参数错误
401	未认证（Token 无效/过期）
403	无权限
404	资源不存在
409	业务冲突（如工号重复）
500	服务器内部错误
API 设计要点总结：

✅ RESTful 风格，资源命名清晰
✅ 每个接口标注权限标识，对接 RBAC 体系
✅ 内部 Feign 接口与外部接口分离（/internal/ 路径）
✅ 统一响应格式 + 错误码规范
✅ 分页、导入导出、批量操作完整覆盖
你觉得 API 接口设计可以吗？ 确认后我将展示前端页面结构设计，然后输出完整设计文档
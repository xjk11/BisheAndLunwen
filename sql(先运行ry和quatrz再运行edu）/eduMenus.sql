-- 成绩信息菜单权限
-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2000', '成绩信息', '0', '3', 'grade',null, 1, 0, 'M', '0', '0', null, '成绩管理', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩', '2000', '1', 'avg', 'education/avg/index', 1, 0, 'C', '0', '0', 'education:avg:list', '#', 'admin', sysdate(), '', null, '班级平均成绩菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:avg:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:avg:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:avg:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:avg:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('班级平均成绩导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:avg:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩', '2000', '1', 'scores', 'education/scores/index', 1, 0, 'C', '0', '0', 'education:scores:list', '#', 'admin', sysdate(), '', null, '学生成绩菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:scores:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:scores:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:scores:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:scores:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生成绩导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:scores:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩', '2000', '1', 'makeup', 'education/makeup/index', 1, 0, 'C', '0', '0', 'education:makeup:list', '#', 'admin', sysdate(), '', null, '补考成绩菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:makeup:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:makeup:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:makeup:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:makeup:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('补考成绩导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:makeup:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩', '2000', '1', 'placement', 'education/placement/index', 1, 0, 'C', '0', '0', 'education:placement:list', '#', 'admin', sysdate(), '', null, '等级考试成绩菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:placement:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:placement:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:placement:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:placement:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('等级考试成绩导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:placement:export',       '#', 'admin', sysdate(), '', null, '');

-- 人员信息菜单权限
-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2025', '人员信息', '0', '4', 'people',null, 1, 0, 'M', '0', '0', null, '人员管理', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人教师查询', '2025', '1', 'selectTeacher', 'education/selectTeacher/index', 1, 0, 'C', '0', '0', 'education:selectTeacher:list', '#', 'admin', sysdate(), '', null, '教师信息查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:selectTeacher:query',        '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人学生查询', '2025', '1', 'selectStudent', 'education/selectStudent/index', 1, 0, 'C', '0', '0', 'education:selectStudent:list', '#', 'admin', sysdate(), '', null, '学生信息查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:selectStudent:query',        '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息', '2025', '1', 'student', 'education/student/index', 1, 0, 'C', '0', '0', 'education:student:list', '#', 'admin', sysdate(), '', null, '学生信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:student:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:student:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:student:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:student:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:student:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息', '2025', '1', 'teacher', 'education/teacher/index', 1, 0, 'C', '0', '0', 'education:teacher:list', '#', 'admin', sysdate(), '', null, '教师信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:teacher:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:teacher:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:teacher:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:teacher:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('教师信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:teacher:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理', '2025', '1', 'teacherStudentRelationship', 'education/teacherStudentRelationship/index', 1, 0, 'C', '0', '0', 'education:teacherStudentRelationship:list', '#', 'admin', sysdate(), '', null, '师生关系管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:teacherStudentRelationship:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:teacherStudentRelationship:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:teacherStudentRelationship:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:teacherStudentRelationship:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('师生关系管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:teacherStudentRelationship:export',       '#', 'admin', sysdate(), '', null, '');

-- 课程信息菜单权限
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2048', '课程信息', '0', '5', 'course',null, 1, 0, 'M', '0', '0', null, '课程信息', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理', '2048', '2', 'course', 'education/course/index', 1, 0, 'C', '0', '0', 'education:course:list', '#', 'admin', sysdate(), '', null, '课程管理菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:course:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:course:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:course:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:course:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('课程管理导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:course:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程', '2048', '1', 'schedule', 'education/schedule/index', 1, 0, 'C', '0', '0', 'education:schedule:list', '#', 'admin', sysdate(), '', null, '个人课程菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:schedule:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:schedule:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:schedule:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:schedule:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课程导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:schedule:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人课表查询', '2048', '1', 'eduTimetable', 'education/eduTimetable/index', 1, 0, 'C', '0', '0', 'education:eduTimetable:list', '#', 'admin', sysdate(), '', null, '个人课表查询菜单');

-- 考试信息菜单权限
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2062', '考试信息', '0', '6', 'exam',null, 1, 0, 'M', '0', '0', null, '考试信息', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息', '2062', '1', 'exam', 'education/exam/index', 1, 0, 'C', '0', '0', 'education:exam:list', '#', 'admin', sysdate(), '', null, '考试信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:exam:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:exam:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:exam:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:exam:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('考试信息导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:exam:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询', '2062', '1', 'userExam', 'education/userExam/index', 1, 0, 'C', '0', '0', 'education:userExam:list', '#', 'admin', sysdate(), '', null, '个人考试信息查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:userExam:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:userExam:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:userExam:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:userExam:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人考试信息查询导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:userExam:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询', '2062', '1', 'userMakeupExam', 'education/userMakeupExam/index', 1, 0, 'C', '0', '0', 'education:userMakeupExam:list', '#', 'admin', sysdate(), '', null, '个人补考信息查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:userMakeupExam:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:userMakeupExam:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:userMakeupExam:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:userMakeupExam:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人补考信息查询导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:userMakeupExam:export',       '#', 'admin', sysdate(), '', null, '');

-- 选课信息菜单权限
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('2088', '选课信息', '0', '7', 'selectCourses',null, 1, 0, 'M', '0', '0', null, '选课管理', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课', '2088', '1', 'selectcourses', 'education/selectcourses/index', 1, 0, 'C', '0', '0', 'education:selectcourses:list', '#', 'admin', sysdate(), '', null, '学生选课菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:selectcourses:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:selectcourses:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:selectcourses:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:selectcourses:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('学生选课导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:selectcourses:export',       '#', 'admin', sysdate(), '', null, '');

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询', '2088', '1', 'userselectcourses', 'education/userselectcourses/index', 1, 0, 'C', '0', '0', 'education:userselectcourses:list', '#', 'admin', sysdate(), '', null, '个人选课信息查询菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'education:userselectcourses:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'education:userselectcourses:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'education:userselectcourses:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'education:userselectcourses:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('个人选课信息查询导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'education:userselectcourses:export',       '#', 'admin', sysdate(), '', null, '');

-- 权限插入
-- 学生权限
insert into sys_role_menu values
('100', '2000'),
('100', '2007'),
('100', '2008'),
('100', '2012'),
('100', '2013'),
('100', '2014'),
('100', '2018'),
('100', '2019'),
('100', '2020'),
('100', '2024'),
('100', '2025'),
('100', '2026'),
('100', '2027'),
('100', '2048'),
('100', '2055'),
('100', '2056'),
('100', '2060'),
('100', '2061'),
('100', '2062'),
('100', '2069'),
('100', '2070'),
('100', '2074'),
('100', '2075'),
('100', '2076'),
('100', '2080'),
('100', '2088'),
('100', '2089'),
('100', '2090'),
('100', '2095'),
('100', '2096'),
('100', '2099');

-- 教师权限
insert into sys_role_menu values
('101', '2000'),
('101', '2001'),
('101', '2002'),
('101', '2006'),
('101', '2007'),
('101', '2008'),
('101', '2009'),
('101', '2010'),
('101', '2011'),
('101', '2012'),
('101', '2013'),
('101', '2014'),
('101', '2015'),
('101', '2016'),
('101', '2017'),
('101', '2018'),
('101', '2025'),
('101', '2028'),
('101', '2029'),
('101', '2048'),
('101', '2055'),
('101', '2056'),
('101', '2060'),
('101', '2061'),
('101', '2062'),
('101', '2069'),
('101', '2070'),
('101', '2074'),
('101', '2075'),
('101', '2076'),
('101', '2080');
-- 添加部门
insert into sys_dept values(200,  100,  '0,100',  '信息与通信学院',   1, '陈老师', '16666666666', 'teacherchen@example.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(201,  100,  '0,100',  '外语学院',   2, '李老师', '16666666667', 'teacherli@example.com', '0', '0', 'admin', sysdate(), '', null);
insert into sys_dept values(202,  100,  '0,100',  '数学学院',   3, '宋老师', '16666666668', 'teachersong@example.com', '0', '0', 'admin', sysdate(), '', null);

-- 插入学生用户
INSERT INTO sys_user (user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark)
VALUES
(2, 201, 'stu1', '学生张三', '01', 'zhangsan@example.com', '13800138000', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '学生'),
(3, 200, 'stu2', '学生李四', '01', 'lisi@example.com', '13800138001', '1', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '学生'),
(4, 201, 'stu3', '学生王五', '01', 'wangwu@example.com', '13800138002', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '学生'),
(5, 202, 'stu4', '学生赵六', '01', 'zhaoliu@example.com', '13800138003', '1', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '学生'),
(6, 200, 'xjk', '学生谢骏凯', '01', 'xjk@example.com', '13800138004', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '学生');

-- 插入教师用户
INSERT INTO sys_user (user_id, dept_id, user_name, nick_name, user_type, email, phonenumber, sex, avatar, password, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark)
VALUES
(7, 201, 't1', '老师张三', '01', 'zhangsan@example.com', '13800138005', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '老师'),
(8, 200, 't2', '老师李四', '01', 'lisi@example.com', '13800138006', '1', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '老师'),
(9, 201, 't3', '老师王五', '01', 'wangwu@example.com', '13800138007', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '老师'),
(10, 202, 't4', '老师赵六', '01', 'zhaoliu@example.com', '13800138008', '1', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '老师'),
(11, 200, 't5', '陈老师', '01', 'xjk@example.com', '13800138009', '0', '', '$2a$10$VT33RaxTr7nKS1CoTI54iONLJQH7KuD4R45E.vlq6tiawo7MVD1mO', '0', '0', '', NULL, 'admin', SYSDATE(), 'admin', SYSDATE(), '老师');

-- 创建角色:学生
insert into sys_role(role_id, role_name, role_key, role_sort, data_scope, menu_check_strictly, dept_check_strictly, status, del_flag, create_by, create_time, update_by, update_time, remark)
values ('100', '学生', 'student', 2, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, '学生');

-- 创建角色:教师
insert into sys_role(role_id, role_name, role_key, role_sort, data_scope, menu_check_strictly, dept_check_strictly, status, del_flag, create_by, create_time, update_by, update_time, remark)
values ('101', '教师', 'teacher', 3, 1, 1, 1, '0', '0', 'admin', sysdate(), '', null, '教师');

-- 将五个学生添加成为学生身份
insert into sys_user_role(user_id, role_id) VALUES (2,100),(3,100),(4,100),(5,100),(6,100);
-- 将五个教师添加成为教师身份
insert into sys_user_role(user_id, role_id) VALUES (7,101),(8,101),(9,101),(10,101),(11,101);

drop table if exists edu_class;
CREATE TABLE edu_class (
    class_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '班级ID',
    class_name VARCHAR(255) NOT NULL COMMENT '班级名称',
    major VARCHAR(255) NOT NULL COMMENT '专业',
    faculties VARCHAR(255) NOT NULL COMMENT '学院'
) COMMENT='班级表';

-- 插入数据
INSERT INTO edu_class (class_name, major, faculties) VALUES
('计科211', '计算机科学与技术', '信息与通信学院'),
('软工211', '软件工程', '信息与通信学院'),
('信息211', '信息管理与信息系统', '信息与通信学院'),
('网络211', '网络工程', '信息与通信学院'),
('计科214', '计算机科学与技术', '信息与通信学院');

-- 创建学生表
drop table if exists edu_students;
CREATE TABLE edu_students (
    user_id bigint(20) COMMENT '用户id',
    student_id bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '学生唯一标识符',
    name VARCHAR(20) NOT NULL COMMENT '姓名',
    gender char(1) default '0' COMMENT '性别（0表示男，1表示女）',
    birth_date DATE COMMENT '出生日期',
    major VARCHAR(30) COMMENT '专业',
    dept_id bigint(20) comment '部门id',
    class_id BIGINT(20) COMMENT '班级id',
    enrollment_date DATE COMMENT '入学时间',
    create_by         varchar(64)     default ''   comment '创建者',
    create_time 	    datetime        comment '创建时间',
    update_by         varchar(64)     default ''   comment '更新者',
    update_time       datetime        comment '更新时间',
    FOREIGN KEY (class_id) REFERENCES edu_class(class_id),
    FOREIGN KEY (dept_id) REFERENCES sys_dept(dept_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
) COMMENT='学生信息表';

-- 插入 5 条示例数据
INSERT INTO edu_students (user_id,name, gender, birth_date, major, dept_id, class_id, enrollment_date, create_by, create_time, update_by, update_time) VALUES
(2,'张三', 0, '2000-05-15', '计算机科学与技术',201, 1, '2018-09-01', 'admin', NOW(), 'admin', NOW()),
(3,'李四', 1, '1999-08-20', '软件工程', 200, 2, '2018-09-01', 'admin', NOW(), 'admin', NOW()),
(4,'王五', 0, '2001-12-10', '信息管理与信息系统',201, 3, '2019-09-01', 'admin', NOW(), 'admin', NOW()),
(5,'赵六', 1, '1998-07-25', '网络工程', 202, 4, '2018-09-01', 'admin', NOW(), 'admin', NOW()),
(6,'谢骏凯', 0, '2000-03-05', '计算机科学与技术',200, 5, '2019-09-01', 'admin', NOW(), 'admin', NOW());

drop table if exists edu_teachers;
CREATE TABLE edu_teachers (
    teacher_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '教师唯一标识符',
    user_id BIGINT(20) COMMENT '用户id',
    name VARCHAR(20) NOT NULL COMMENT '姓名',
    gender char(1)  default '0' COMMENT '性别（0表示男，1表示女）',
    title VARCHAR(20) COMMENT '职称',
    dept_id BIGINT COMMENT '所属院系',
    hire_date DATE COMMENT '入职时间',
    create_by VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME COMMENT '创建时间',
    update_by VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME COMMENT '更新时间',
    FOREIGN KEY (dept_id) REFERENCES sys_dept(dept_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
) COMMENT='教师信息表';

-- 插入 5 条示例数据
INSERT INTO edu_teachers (teacher_id, user_id, name, gender, title, dept_id, hire_date, create_by, create_time, update_by, update_time)
VALUES
(1, 7, '张三老师', '0', '教授', 201, '2010-08-01', 'admin', SYSDATE(), 'admin', SYSDATE()),
(2, 8, '李四老师', '1', '副教授', 200, '2012-09-01', 'admin', SYSDATE(), 'admin', SYSDATE()),
(3, 9, '王五老师', '0', '讲师', 201, '2015-07-01', 'admin', SYSDATE(), 'admin', SYSDATE()),
(4, 10, '赵六老师', '1', '助教', 202, '2018-06-01', 'admin', SYSDATE(), 'admin', SYSDATE()),
(5, 11, '陈老师', '0', '教授', 200, '2009-10-01', 'admin', SYSDATE(), 'admin', SYSDATE());


drop table if exists edu_tea_stu;
CREATE TABLE edu_tea_stu(
    tea_stu_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '教师学生关联唯一标识符',
    teacher_id BIGINT(20) COMMENT '教师唯一标识符',
    student_id bigint(20)  COMMENT '学生唯一标识符',
    UNIQUE KEY unique_teacher_student (teacher_id, student_id),  -- 唯一约束
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id),
    FOREIGN KEY (student_id) REFERENCES edu_students(student_id)
) COMMENT='教师学生关联表';

-- 插入数据
INSERT INTO edu_tea_stu (teacher_id, student_id) VALUES
(1, 1), (2, 1), (3, 1),
(2, 2), (3, 2), (4, 2),
(3, 3), (4, 3), (5, 3),
(4, 4), (5, 4), (1, 4),
(5, 5), (1, 5), (2, 5);

-- 创建 edu_course 表
drop table if exists edu_course;
CREATE TABLE edu_course (
    course_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '课程id',
    course_name VARCHAR(255) NOT NULL COMMENT '课程名称',
    course_description TEXT COMMENT '课程描述',
    credits INT COMMENT '学分',
    dept_id BIGINT(20) COMMENT '开课院系，外键关联 sys_dept 表',
    FOREIGN KEY (dept_id) REFERENCES sys_dept(dept_id)
) COMMENT='课程表';

-- 插入示例数据到 edu_course 表
INSERT INTO edu_course (course_name, course_description, credits, dept_id) VALUES
('计算机网络', '计算机网络基础课程', 3, 200),
('高等数学', '高等数学课程', 4, 201),
('数据结构', '数据结构与算法', 3, 200),
('线性代数', '线性代数课程', 3, 201),
('操作系统', '操作系统原理', 3, 202),
('数据库系统', '数据库系统原理', 4, 202),
('编译原理', '编译原理课程', 3, 200),
('概率论与数理统计', '概率论与数理统计课程', 3, 201),
('计算机组成原理', '计算机组成原理课程', 3, 202),
('软件工程', '软件工程课程', 4, 200);

-- 课程表
-- 创建 edu_schedule 表
drop table if exists edu_schedule;
CREATE TABLE edu_schedule (
    schedule_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '课程安排id',
    user_id BIGINT(20) COMMENT '用户ID，外键关联 sys_user 表',
    course_id BIGINT(20) COMMENT '课程ID，外键关联 edu_course 表',
    teacher_id BIGINT(20) COMMENT '教师ID，外键关联 edu_teachers 表',
    classroom VARCHAR(50) COMMENT '上课教室',
    lesson_time VARCHAR(30) COMMENT '上课时间 值为1到5 数据字典为上课时间',
    start_time VARCHAR(10) COMMENT '开课学年 值为1到5，从2023上开始 数据字典为开课学年',
    day_of_week VARCHAR(20) COMMENT '上课星期几',
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id),
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id)
) COMMENT='个人课程表';

-- 插入示例数据到 edu_schedule 表
INSERT INTO edu_schedule (user_id, course_id, teacher_id, classroom, lesson_time, start_time, day_of_week) VALUES
(2, 1, 1, 'A101', '1', '1', '周一'),
(2, 2, 2, 'B102', '2', '1', '周二'),
(2, 3, 3, 'A103', '3', '1', '周三'),
(2, 4, 4, 'B104', '4', '1', '周四'),
(2, 5, 5, 'A105', '5', '1', '周五'),
(3, 6, 1, 'B106', '1', '2', '周一'),
(3, 7, 2, 'A107', '2', '2', '周二'),
(3, 8, 3, 'B108', '3', '2', '周三'),
(3, 9, 4, 'A109', '4', '2', '周四'),
(3, 10, 5, 'B110', '5', '2', '周五'),
(4, 1, 1, 'A111', '1', '3', '周一'),
(4, 2, 2, 'B112', '2', '3', '周二'),
(4, 3, 3, 'A113', '3', '3', '周三'),
(4, 4, 4, 'B114', '4', '3', '周四'),
(4, 5, 5, 'A115', '5', '3', '周五'),
(5, 6, 1, 'B116', '1', '4', '周一'),
(5, 7, 2, 'A117', '2', '4', '周二'),
(5, 8, 3, 'B118', '3', '4', '周三'),
(5, 9, 4, 'A119', '4', '4', '周四'),
(5, 10, 5, 'B120', '5', '4', '周五'),
(6, 1, 1, 'A121', '1', '5', '周一'),
(6, 2, 2, 'B122', '2', '5', '周二'),
(6, 3, 3, 'A123', '3', '5', '周三'),
(6, 4, 4, 'B124', '4', '5', '周四'),
(6, 5, 5, 'A125', '5', '5', '周五'),
(7, 6, 1, 'B126', '1', '1', '周六'),
(7, 7, 1, 'A127', '2', '1', '周日'),
(7, 8, 1, 'B128', '3', '1', '周一'),
(7, 9, 1, 'A129', '4', '1', '周二'),
(7, 10, 1, 'B130', '5', '1', '周三'),
(8, 1, 2, 'A131', '1', '2', '周四'),
(8, 2, 2, 'B132', '2', '2', '周五'),
(8, 3, 2, 'A133', '3', '2', '周六'),
(8, 4, 2, 'B134', '4', '2', '周日'),
(8, 5, 2, 'A135', '5', '2', '周一'),
(9, 6, 3, 'B136', '1', '3', '周二'),
(9, 7, 3, 'A137', '2', '3', '周三'),
(9, 8, 3, 'B138', '3', '3', '周四'),
(9, 9, 3, 'A139', '4', '3', '周五'),
(9, 10, 3, 'B140', '5', '3', '周六'),
(10, 1, 4, 'A141', '1', '4', '周日'),
(10, 2, 4, 'B142', '2', '4', '周一'),
(10, 3, 4, 'A143', '3', '4', '周二'),
(10, 4, 4, 'B144', '4', '4', '周三'),
(10, 5, 4, 'A145', '5', '4', '周四'),
(11, 6, 5, 'B146', '1', '5', '周五'),
(11, 7, 5, 'A147', '2', '5', '周六'),
(11, 8, 5, 'B148', '3', '5', '周日'),
(11, 9, 5, 'A149', '4', '5', '周一'),
(11, 10, 5, 'B150', '5', '5', '周二');

-- 考试表
drop table if exists edu_exam;
create table edu_exam(
    exam_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '考试记录ID',
    course_id BIGINT(20) NOT NULL COMMENT '课程ID',
    start_time VARCHAR(10) COMMENT '开课学年 值为1到5，从2023上开始 数据字典为开课学年',
    exam_time VARCHAR(30)  COMMENT '考试时间',
    exam_classroom VARCHAR(30)  COMMENT '考试教室',
    PRIMARY KEY (exam_id),
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id)
)COMMENT='考试信息表';

INSERT INTO edu_exam (course_id, start_time, exam_time, exam_classroom) VALUES
(1, '1', '2023-04-10 09:00:00', '教室101'),
(2, '2', '2023-04-12 14:00:00', '教室102'),
(3, '3', '2023-04-14 09:00:00', '教室103'),
(4, '4', '2023-04-16 14:00:00', '教室104'),
(5, '5', '2023-04-18 09:00:00', '教室105'),
(6, '1', '2023-04-20 14:00:00', '教室106'),
(7, '2', '2023-04-22 09:00:00', '教室107'),
(8, '3', '2023-04-24 14:00:00', '教室108'),
(9, '4', '2023-04-26 09:00:00', '教室109'),
(10, '5', '2023-04-28 14:00:00', '教室110');


drop table if exists edu_exam_user;
create table edu_exam_user(
    user_exam_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '个人考试关联id',
    exam_id BIGINT(20) NOT NULL COMMENT '考试记录ID',
    user_id bigint(20) not null comment '用户ID',
    UNIQUE KEY unique_exam_user (exam_id, user_id),
    FOREIGN KEY (exam_id) REFERENCES edu_exam(exam_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
)COMMENT='考试信息用户信息关联表';

INSERT INTO edu_exam_user (exam_id, user_id) VALUES
(1, 2), (2, 2), (3, 2),
(4, 3), (5, 3), (6, 3),
(7, 4), (8, 4), (9, 4),
(10, 5), (1, 5), (2, 5),
(3, 6), (4, 6), (5, 6),
(6, 7), (7, 7), (8, 7),
(9, 8), (10, 8), (1, 8),
(2, 9), (3, 9), (4, 9),
(5, 10), (6, 10), (7, 10),
(8, 11), (9, 11), (10, 11);

drop table if exists edu_makeupexam_user;
create table edu_makeupexam_user(
    user_makeupexam_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '个人考试关联id',
    exam_id BIGINT(20) NOT NULL COMMENT '考试记录ID',
    user_id bigint(20) not null comment '用户ID',
    UNIQUE KEY unique_exam_user (exam_id, user_id),
    FOREIGN KEY (exam_id) REFERENCES edu_exam(exam_id),
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
)COMMENT='补考信息用户信息关联表';

INSERT INTO edu_makeupexam_user (exam_id, user_id) VALUES
(3, 2),
(6, 3),
(9, 4),
(2, 5),
(5, 6),
(8, 7),
(1, 8),
(4, 9),
(7, 10),
(10, 11);

-- 成绩表
drop table if exists edu_scores;
CREATE TABLE edu_scores (
    score_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '成绩记录ID',
    student_id BIGINT(20) NOT NULL COMMENT '学生ID',
    course_id BIGINT(20) NOT NULL COMMENT '课程ID',
    teacher_id BIGINT(20) NOT NULL COMMENT '教师ID',
    exam_score DECIMAL(5,0) COMMENT '考试成绩',
    total_score VARCHAR(10) COMMENT '总成绩 值为1到5，包括优秀,良好,中等,及格,不及格',
    start_time VARCHAR(10) COMMENT '开课学年 值为1到5，从2023上开始 数据字典为开课学年',
    PRIMARY KEY (score_id),
    FOREIGN KEY (student_id) REFERENCES edu_students(student_id),
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id),
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id)
);

INSERT INTO edu_scores (student_id, course_id, teacher_id, exam_score, total_score, start_time) VALUES
-- 学生1
(1, 1, 1, 85, '1', '1'),
(1, 2, 2, 78, '2', '1'),
(1, 3, 3, 92, '1', '2'),
(1, 4, 4, 88, '1', '2'),
(1, 5, 5, 76, '2', '3'),
(1, 6, 1, 82, '2', '3'),
(1, 7, 2, 79, '2', '4'),
(1, 8, 3, 85, '1', '4'),
(1, 9, 4, 74, '3', '5'),
(1, 10, 5, 68, '4', '5'),

-- 学生2
(2, 1, 2, 90, '1', '1'),
(2, 2, 3, 85, '1', '1'),
(2, 3, 4, 78, '2', '2'),
(2, 4, 5, 82, '2', '2'),
(2, 5, 1, 76, '2', '3'),
(2, 6, 2, 88, '1', '3'),
(2, 7, 3, 80, '2', '4'),
(2, 8, 4, 75, '3', '4'),
(2, 9, 5, 70, '4', '5'),
(2, 10, 1, 65, '4', '5'),

-- 学生3
(3, 1, 3, 85, '1', '1'),
(3, 2, 4, 80, '2', '1'),
(3, 3, 5, 75, '2', '2'),
(3, 4, 1, 82, '2', '2'),
(3, 5, 2, 78, '2', '3'),
(3, 6, 3, 88, '1', '3'),
(3, 7, 4, 81, '2', '4'),
(3, 8, 5, 76, '2', '4'),
(3, 9, 1, 73, '3', '5'),
(3, 10, 2, 69, '4', '5'),

-- 学生4
(4, 1, 4, 90, '1', '1'),
(4, 2, 5, 85, '1', '1'),
(4, 3, 1, 80, '2', '2'),
(4, 4, 2, 78, '2', '2'),
(4, 5, 3, 76, '2', '3'),
(4, 6, 4, 82, '2', '3'),
(4, 7, 5, 88, '1', '4'),
(4, 8, 1, 81, '2', '4'),
(4, 9, 2, 75, '3', '5'),
(4, 10, 3, 70, '4', '5'),

-- 学生5
(5, 1, 5, 85, '1', '1'),
(5, 2, 1, 80, '2', '1'),
(5, 3, 2, 78, '2', '2'),
(5, 4, 3, 76, '2', '2'),
(5, 5, 4, 82, '2', '3'),
(5, 6, 5, 88, '1', '3'),
(5, 7, 1, 81, '2', '4'),
(5, 8, 2, 75, '3', '4'),
(5, 9, 3, 70, '4', '5'),
(5, 10, 4, 55, '5', '5');


drop table if exists edu_placement;
CREATE TABLE edu_placement (
    grade_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    user_id BIGINT(20) NOT NULL COMMENT '用户id',
    start_time VARCHAR(10) COMMENT '开课学年 值为1到5，从2023上开始 数据字典为开课学年',
    exam_date DATE NOT NULL COMMENT '考试日期',
    exam_name VARCHAR(255) NOT NULL COMMENT '考试名称 值为1,2,对应4级和6级',
    score DECIMAL(5, 0) NOT NULL COMMENT '成绩',
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id)
) COMMENT='等级考试表';

-- 插入数据
INSERT INTO edu_placement (user_id, start_time, exam_date, exam_name, score) VALUES
(2, '1', '2023-09-01', '1', 485),
(3, '2', '2023-09-02', '2', 490),
(4, '3', '2023-09-03', '1', 478),
(5, '4', '2023-09-04', '2', 588),
(5, '5', '2023-09-04', '2', 463),
(6, '5', '2023-09-05', '1', 392);


drop table if exists edu_makeup;
CREATE TABLE edu_makeup (
    makeup_id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '成绩记录ID',
    student_id BIGINT(20) NOT NULL COMMENT '学生ID',
    course_id BIGINT(20) NOT NULL COMMENT '课程ID',
    teacher_id BIGINT(20) NOT NULL COMMENT '教师ID',
    exam_date DATE NOT NULL COMMENT '考试日期',
    exam_score DECIMAL(5,0) COMMENT '考试成绩',
    makeup_score VARCHAR(10) COMMENT '补考成绩 值为4到5，包括及格,不及格',
    start_time VARCHAR(10) COMMENT '开课学年 值为1到5，从2023上开始 数据字典为开课学年',
    PRIMARY KEY (makeup_id),
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id),
    FOREIGN KEY (student_id) REFERENCES edu_students(student_id),
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id)
);

-- 插入数据
INSERT INTO edu_makeup (student_id, course_id, teacher_id, exam_date, exam_score, makeup_score, start_time) VALUES
(1, 1, 1,'2023-09-01', 55, '5', '1'),
(1, 2, 1,'2023-09-02', 45, '5', '1'),
(2, 3, 2,'2023-09-03', 30, '5', '2'),
(2, 4, 2,'2023-09-04', 60, '4', '2'),
(3, 5, 3,'2023-09-05', 40, '5', '3'),
(3, 6, 3,'2023-09-06', 70, '4', '3'),
(4, 7, 4,'2023-09-07', 50, '5', '4'),
(4, 8, 4,'2023-09-08', 80, '4', '4'),
(5, 9, 5,'2023-09-09', 45, '5', '5'),
(5, 10, 5,'2023-09-10', 55, '5', '5');


drop table if exists edu_avg;
create table edu_avg (
    avg_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '平均分ID',
    teacher_id BIGINT(20) NOT NULL COMMENT '教师ID',
    course_id BIGINT(20) NOT NULL COMMENT '课程ID',
    class_id BIGINT(20) NOT NULL COMMENT '班级ID',
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id),
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id),
    FOREIGN KEY (class_id) REFERENCES edu_class(class_id)
) COMMENT='平均分表';

INSERT INTO edu_avg (teacher_id, course_id, class_id) VALUES
(1, 1, 1),
(1, 2, 1),
(2, 3, 1),
(2, 4, 1),
(3, 5, 1),
(3, 6, 1),
(4, 7, 1),
(4, 8, 1),
(5, 9, 1),
(5, 10, 1),

(1, 1, 2),
(1, 2, 2),
(2, 3, 2),
(2, 4, 2),
(3, 5, 2),
(3, 6, 2),
(4, 7, 2),
(4, 8, 2),
(5, 9, 2),
(5, 10, 2),

(1, 1, 3),
(1, 2, 3),
(2, 3, 3),
(2, 4, 3),
(3, 5, 3),
(3, 6, 3),
(4, 7, 3),
(4, 8, 3),
(5, 9, 3),
(5, 10, 3),

(1, 1, 4),
(1, 2, 4),
(2, 3, 4),
(2, 4, 4),
(3, 5, 4),
(3, 6, 4),
(4, 7, 4),
(4, 8, 4),
(5, 9, 4),
(5, 10, 4),

(1, 1, 5),
(1, 2, 5),
(2, 3, 5),
(2, 4, 5),
(3, 5, 5),
(3, 6, 5),
(4, 7, 5),
(4, 8, 5),
(5, 9, 5),
(5, 10, 5);

-- 选课表
drop table if exists edu_selectcourses;
CREATE TABLE edu_selectcourses (
    select_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '选课记录ID',
    course_id BIGINT(20) NOT NULL COMMENT '课程ID，外键关联edu_course表',
    teacher_id BIGINT(20) NOT NULL COMMENT '教师ID，外键关联edu_teachers表',
    remaining_seats INT NOT NULL COMMENT '选课剩余',
    start_time VARCHAR(10) COMMENT '开课学年，值为1到5，从2023上开始，数据字典为开课学年',
    FOREIGN KEY (course_id) REFERENCES edu_course(course_id),
    FOREIGN KEY (teacher_id) REFERENCES edu_teachers(teacher_id)
);

INSERT INTO edu_selectcourses (course_id, teacher_id, remaining_seats,start_time) VALUES
(1, 1, 2, 1),
(2, 2, 2, 2),
(3, 3, 2, 3),
(4, 4, 2, 4),
(5, 5, 2, 5),
(6, 1, 2, 1),
(7, 2, 2, 2),
(8, 3, 2, 3),
(9, 4, 2, 4),
(10, 5, 2, 5);

drop table if exists edu_userselectcourses;
CREATE TABLE edu_userselectcourses (
    user_select_id BIGINT(20) AUTO_INCREMENT PRIMARY KEY COMMENT '用户选课记录ID',
    user_id BIGINT(20) NOT NULL COMMENT '用户ID，外键关联sys_user表',
    select_id BIGINT(20) NOT NULL COMMENT '选课记录ID，外键关联edu_selectcourses表',
    FOREIGN KEY (user_id) REFERENCES sys_user(user_id),
    FOREIGN KEY (select_id) REFERENCES edu_selectcourses(select_id)
);

INSERT INTO edu_userselectcourses (user_id, select_id) VALUES
(2, 1),
(3, 2),
(4, 3),
(5, 4),
(6, 5);

-- 插入字典数据
insert into sys_dict_type values
(100, '性别', 'sex',   '0', 'admin', sysdate(), '', null, null),
(101, '学院', 'edu_faculties',   '0', 'admin', sysdate(), '', null, null),
(102, '开课时间', 'course_duration',   '0', 'admin', sysdate(), '', null, null),
(103, '上课时间', 'lesson_time',   '0', 'admin', sysdate(), '', null, null),
(104, '总成绩', 'total_score',   '0', 'admin', sysdate(), '', null, null),
(105, '考试名称', 'exam_name',   '0', 'admin', sysdate(), '', null, null);

insert into sys_dict_data values
(100,  0,  '男',       '0',       'sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(101,  1,  '女',       '1',       'sex',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null);
insert into sys_dict_data values
(102,  0,  '信息与通信学院',       '200',       'edu_faculties',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(103,  1,  '外语学院',       '201',       'edu_faculties',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(104,  2,  '数学学院',       '202',       'edu_faculties',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(105,  1,  '2023-2024上半年',       '1',       'course_duration',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(106,  2,  '2023-2024下半年',       '2',       'course_duration',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(107,  3,  '2024-2025上半年',       '3',       'course_duration',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(108,  4,  '2024-2025下半年',       '4',       'course_duration',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(109,  5,  '2025-2026上半年',       '5',       'course_duration',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(110,  1,  '上午1、2节',       '1',       'lesson_time',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(111,  2,  '上午3、4节',       '2',       'lesson_time',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(112,  3,  '下午1、2节',       '3',       'lesson_time',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(113,  4,  '下午3、4节',       '4',       'lesson_time',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(114,  5,  '晚上1、2节',       '5',       'lesson_time',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(115,  1,  '优秀',       '1',       'total_score',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(116,  2,  '良好',       '2',       'total_score',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(117,  3,  '中等',       '3',       'total_score',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(118,  4,  '及格',       '4',       'total_score',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(119,  5,  '不及格',       '5',       'total_score',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(120,  1,  '英语四级考试',       '1',       'exam_name',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null),
(121,  2,  '英语六级考试',       '2',       'exam_name',        '',   '',        'N', '0', 'admin', sysdate(), '', null, null);

-- 插入定时任务
insert into sys_job values
(100, '定时输出当前时间', 'DEFAULT', 'myTask.showTime()', '0/5 * * * * ?', '3', '1', '1', 'admin', sysdate(), '', null, ''),
(101, '定制时间', 'DEFAULT', 'myTask.showTime()', '* * 20 21 * ?', '3', '1', '1', 'admin', sysdate(), '', null, ''),
(102, '开启选课', 'DEFAULT', 'eduSelectCourseTask.openSelectCourse', '* * 20 21 * ?', '3', '1', '1', 'admin', sysdate(), '', null, ''),
(103, '停止选课', 'DEFAULT', 'eduSelectCourseTask.closeSelectCourse', '* * 20 25 * ?', '3', '1', '1', 'admin', sysdate(), '', null, '');




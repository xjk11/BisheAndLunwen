package com.xjk.education.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 学生信息对象 edu_students
 *
 * @author xjk
 * @date 2025-01-22
 */
@Data
public class EduStudents extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 学生唯一标识符 */
//    @Excel(name = "学生唯一标识符")
    private Long studentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别（0表示男，1表示女） */
    @Excel(name = "性别（0表示男，1表示女）")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 学院 */
    @Excel(name = "学院")
    private String deptId;

    /** 班级id */
//    @Excel(name = "班级id")
    private String classId;

    @Excel(name = "班级名")
    private String className;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollmentDate;

    private String phoneNumber;

}

package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduTeacherSelectStudentVo {

    @Excel(name = "教师用户id")
    private Long teacherUserId;

    @Excel(name = "教师姓名")
    private String teacherName;

    @Excel(name = "学生名")
    private String studentName;

    @Excel(name = "学生性别")
    private String studentGender;

    @Excel(name = "学生专业")
    private String studentMajor;

    @Excel(name = "学生学院")
    private String deptId;

    @Excel(name = "学生班级")
    private String studentClass;

    @Excel(name = "学生手机号")
    private String studentPhonenumber;

    @Excel(name = "入学时间")
    private String studentEnrollmentDate;
}

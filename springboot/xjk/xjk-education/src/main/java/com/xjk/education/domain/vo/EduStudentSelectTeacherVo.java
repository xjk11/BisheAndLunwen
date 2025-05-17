package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduStudentSelectTeacherVo {

    @Excel(name = "学生用户id")
    private Long studentUserId;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "教师名")
    private String teacherName;

    @Excel(name = "教师性别")
    private String teacherGender;

    @Excel(name = "教师学院")
    private String deptId;

    @Excel(name = "教师职称")
    private String teacherTitle;

    @Excel(name = "教师入职时间")
    private String teacherHireDate;

    @Excel(name = "教师手机号")
    private String teacherPhonenumber;
}

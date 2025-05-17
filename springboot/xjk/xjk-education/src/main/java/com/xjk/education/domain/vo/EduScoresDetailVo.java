package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduScoresDetailVo {
    private Long scoreId;
    @Excel(name = "学生姓名")
    private String studentName;
    @Excel(name = "班级名")
    private String className;
    @Excel(name = "开课学年")
    private String academicYear;
    private String courseId;
    @Excel(name = "课程名")
    private String courseName;
    @Excel(name = "教师名")
    private String teacherName;
    private Integer credits;
    @Excel(name = "考试成绩")
    private Double examScore;
    @Excel(name = "总成绩")
    private String totalScore;
    @Excel(name = "学院")
    private String departmentId;
    private Long stuUserId;
    private Long teaUserId;
}

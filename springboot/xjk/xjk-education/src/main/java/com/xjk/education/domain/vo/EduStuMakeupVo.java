package com.xjk.education.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjk.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class EduStuMakeupVo {
    private Long makeupId;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "教师姓名")
    private String teacherName;

    private Long userId;

    @Excel(name = "课程名称")
    private String courseName;

    @Excel(name = "考试分数")
    private Long examScore;

    @Excel(name = "补考成绩")
    private String makeupScore;

    @Excel(name = "开课学年")
    private String startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试时间", dateFormat = "yyyy-MM-dd")
    private Date examDate;
}

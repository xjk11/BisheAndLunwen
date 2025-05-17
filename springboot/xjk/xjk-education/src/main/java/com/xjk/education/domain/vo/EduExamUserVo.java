package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduExamUserVo {

    private Long userExamId;
    private Long userMakeupexamId;

    @Excel(name = "用户id")
    private Long userId;

    @Excel(name = "用户名")
    private String userName;

    private Long examId;

    @Excel(name = "课程编号")
    private Long courseId;

    @Excel(name = "课程名")
    private String courseName;

    @Excel(name = "开课学年")
    private String startTime;

    @Excel(name = "考试时间")
    private String examTime;

    @Excel(name = "考试教室")
    private String examClassroom;
}

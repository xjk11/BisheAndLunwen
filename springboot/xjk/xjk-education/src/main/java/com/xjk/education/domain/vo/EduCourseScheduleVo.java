package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduCourseScheduleVo {
    private Long scheduleId;

    @Excel(name = "用户名")
    private String userName;

    @Excel(name = "课程名")
    private String courseName;

    @Excel(name = "上课教师")
    private String teacherName;

    @Excel(name = "课室")
    private String classroom;

    @Excel(name = "星期几")
    private String dayOfWeek;

    @Excel(name = "第几节课")
    private String lessonTime;

    @Excel(name = "开课学年")
    private String startTime;

}

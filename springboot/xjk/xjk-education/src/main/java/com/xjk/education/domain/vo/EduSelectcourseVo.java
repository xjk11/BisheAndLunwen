package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduSelectcourseVo {
    private Long userId;
    private Long userSelectId;

    @Excel(name = "选课编号")
    private Long selectId;

    @Excel(name = "课程编号")
    private Long courseId;

    @Excel(name = "课程名")
    private String courseName;

    private String courseDescription;

    @Excel(name = "学分")
    private Integer credits;

    @Excel(name = "教师名")
    private String teacherName;

    @Excel(name = "学生名")
    private String studentName;

    @Excel(name = "课程剩余量")
    private Integer remainingSeats;

    @Excel(name = "开课学年")
    private String startTime;
}

package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduAvgVo {

    private Long avgId;

    @Excel(name = "班级")
    private String className;
    @Excel(name = "课程")
    private String courseName;

    private Long teacherUserId;
    @Excel(name = "平均成绩")
    private Double averageScore;
}

package com.xjk.education.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjk.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class EduMakeupTeacherImportVo {
    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 考试日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examDate;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private Long examScore;

    /** 补考成绩 */
    @Excel(name = "补考成绩")
    private String makeupScore;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;
}

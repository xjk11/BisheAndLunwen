package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import com.xjk.education.domain.EduExam;
import lombok.Data;

@Data
public class EduExamVo {

    /** 考试记录ID */
    @Excel(name = "考试记录ID")
    private Long examId;

    @Excel(name = "课程名")
    private String courseName;

    /** 课程ID */
    private Long courseId;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;

    /** 考试教室 */
    @Excel(name = "考试教室")
    private String examClassroom;

    /** 考试时间 */
    @Excel(name = "考试时间")
    private String examTime;

}

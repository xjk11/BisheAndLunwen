package com.xjk.education.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 考试信息对象 edu_exam
 *
 * @author xjk
 * @date 2025-02-07
 */
@Data
public class EduExam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试记录ID */
//    @Excel(name = "考试记录ID")
    private Long examId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    private String courseName;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;

    /** 考试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String examTime;

    /** 考试教室 */
    @Excel(name = "考试教室")
    private String examClassroom;

}

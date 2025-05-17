package com.xjk.education.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 补考成绩对象 edu_makeup
 *
 * @author xjk
 * @date 2025-02-03
 */
@Data
public class EduMakeup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩记录ID */
//    @Excel(name = "成绩记录ID")
    private Long makeupId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    @Excel(name = "教师ID")
    private Long teacherId;

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

    private Long userId;

    private String courseName;
    private String studentName;

}

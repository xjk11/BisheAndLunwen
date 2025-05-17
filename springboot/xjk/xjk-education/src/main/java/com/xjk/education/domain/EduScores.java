package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 学生成绩对象 edu_scores
 *
 * @author xjk
 * @date 2025-02-02
 */
@Data
public class EduScores extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩记录ID */
    private Long scoreId;

    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private Long examScore;

    /** 总成绩 */
    @Excel(name = "总成绩（值为1-5）")
    private String totalScore;

    /** 开课学年 */
    @Excel(name = "开课学年（值为1-5）")
    private String startTime;

    private Long stuUserId;
    private Long teaUserId;
    private String courseName;
    private String studentName;
}

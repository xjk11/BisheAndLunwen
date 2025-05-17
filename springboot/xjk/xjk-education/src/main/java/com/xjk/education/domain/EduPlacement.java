package com.xjk.education.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 等级考试成绩对象 edu_placement
 *
 * @author xjk
 * @date 2025-02-03
 */
@Data
public class EduPlacement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long studentId;

    private String studentName;
    /** 主键ID */
    private Long gradeId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;

    /** 考试日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examDate;

    /** 考试名称 */
    @Excel(name = "考试名称")
    private String examName;

    /** 成绩 */
    @Excel(name = "成绩")
    private Long score;

}

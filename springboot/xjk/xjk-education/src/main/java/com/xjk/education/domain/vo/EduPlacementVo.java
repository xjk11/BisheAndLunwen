package com.xjk.education.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjk.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class EduPlacementVo {

    @Excel(name = "学生ID")
    private Long studentId;

    @Excel(name = "学生姓名")
    private String studentName;

    /** 主键ID */
    private Long gradeId;

    /** 用户id */
    private Long userId;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;

    /** 考试名称 */
    @Excel(name = "考试名称")
    private String examName;

    /** 成绩 */
    @Excel(name = "成绩")
    private Long score;

    /** 考试日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考试日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examDate;
}

package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduTeaScoreImportModelVo {
    /** 学生ID */
    @Excel(name = "学生ID")
    private Long studentId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 考试成绩 */
    @Excel(name = "考试成绩")
    private Long examScore;

    /** 总成绩 */
    @Excel(name = "总成绩（值为1-5）")
    private String totalScore;

    /** 开课学年 */
    @Excel(name = "开课学年（值为1-5）")
    private String startTime;

}

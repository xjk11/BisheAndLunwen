package com.xjk.education.domain.vo;

import com.xjk.common.annotation.Excel;
import lombok.Data;

@Data
public class EduTeaStuExportVo {
    /** 教师唯一标识符 */
    @Excel(name = "教师唯一标识符")
    private Long teacherId;

    /** 学生唯一标识符 */
    @Excel(name = "学生唯一标识符")
    private Long studentId;
}

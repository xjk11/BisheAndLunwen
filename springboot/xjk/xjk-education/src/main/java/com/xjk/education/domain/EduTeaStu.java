package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 师生关系管理对象 edu_tea_stu
 *
 * @author xjk
 * @date 2025-03-19
 */
@Data
public class EduTeaStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师学生关联唯一标识符 */
    @Excel(name = "教师学生关联唯一标识符")
    private Long teaStuId;

    /** 教师唯一标识符 */
    @Excel(name = "教师唯一标识符")
    private Long teacherId;

    /** 学生唯一标识符 */
    @Excel(name = "学生唯一标识符")
    private Long studentId;

    /** 教师唯一标识符 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 学生唯一标识符 */
    @Excel(name = "学生姓名")
    private String studentName;

}

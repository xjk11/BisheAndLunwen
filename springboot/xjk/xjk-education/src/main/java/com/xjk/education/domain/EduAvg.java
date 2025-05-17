package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 班级平均成绩对象 edu_avg
 *
 * @author xjk
 * @date 2025-02-03
 */
@Data
public class EduAvg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 平均成绩记录ID */
//    @Excel(name = "平均成绩记录ID")
    private Long avgId;

    /** 教师ID */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 课程ID */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 班级ID */
    @Excel(name = "班级ID")
    private Long classId;

    private Long teacherUserId;

    private String className;

    private String courseName;

}

package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 学生选课对象 edu_selectcourses
 *
 * @author xjk
 * @date 2025-03-04
 */
@Data
public class EduSelectcourses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 选课信息id */
//    @Excel(name = "选课信息id")
    private Long selectId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;

    /** 课程剩余 */
    @Excel(name = "课程剩余")
    private Long remainingSeats;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String startTime;

    private String courseName;
    private String teacherName;
    private String credits;
    private Long userId;
    private Long userSelectId;
}

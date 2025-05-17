package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 课程管理对象 edu_course
 *
 * @author xjk
 * @date 2025-01-23
 */
@Data
public class EduCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程id */
//    @Excel(name = "课程id")
    private Long courseId;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String courseDescription;

    /** 学分 */
    @Excel(name = "学分")
    private Long credits;

    /** 开课院系，外键关联 sys_dept 表 */
    @Excel(name = "开课院系")
    private String deptId;

    //联合查询新增
    private Long userId;

    private String userName;

    private String nickName;

    private String teacherName;

    private String classroom;

    private String lessonTime;

    private String startTime;//开课学年

    private String dayOfWeek;

}

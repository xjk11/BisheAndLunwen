package com.xjk.education.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 个人课程对象 edu_schedule
 *
 * @author xjk
 * @date 2025-01-23
 */
@Data
public class EduSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程安排id */
    private Long scheduleId;

    /** 用户ID，外键关联 sys_user 表 */
    @Excel(name = "用户ID")
    private Long userId;

    /** 课程ID，外键关联 edu_course 表 */
    @Excel(name = "课程ID")
    private Long courseId;

    /** 教师ID，外键关联 edu_teachers 表 */
    @Excel(name = "教师ID")
    private Long teacherId;

    /** 上课教室 */
    @Excel(name = "上课教室")
    private String classroom;

    /** 上课时间 */
    @Excel(name = "上课时间")
    private String lessonTime;

    /** 开课学年 值为1到6，从2023开始 */
    @Excel(name = "开课学年 值为1到6，从2023开始")
    private String startTime;

    /** 上课星期几 */
    @Excel(name = "上课星期几")
    private String dayOfWeek;


}

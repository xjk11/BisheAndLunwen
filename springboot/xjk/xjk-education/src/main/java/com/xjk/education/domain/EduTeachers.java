package com.xjk.education.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 教师信息对象 edu_teachers
 *
 * @author xjk
 * @date 2025-01-22
 */
@Data
public class EduTeachers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师唯一标识符 */
//    @Excel(name = "教师唯一标识符")
    private Long teacherId;

    /** 用户id */
    @Excel(name = "绑定的用户id")
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别（0表示男，1表示女） */
    @Excel(name = "性别")
    private String gender;

    /** 职称 */
    @Excel(name = "职称")
    private String title;

    /** 所属院系 */
    @Excel(name = "所属院系")
    private String deptId;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    private String phoneNumber;
}

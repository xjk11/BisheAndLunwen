package com.xjk.education.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xjk.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class EduStudentImportVo {

    @Excel(name = "用户id")
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别（0表示男，1表示女） */
    @Excel(name = "性别（0表示男，1表示女）")
    private String gender;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthDate;

    /** 专业 */
    @Excel(name = "专业")
    private String major;

    /** 班级id */
    @Excel(name = "班级id")
    private String classId;

    /** 学院 */
    @Excel(name = "学院")
    private String deptId;

    /** 入学时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入学时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date enrollmentDate;

}

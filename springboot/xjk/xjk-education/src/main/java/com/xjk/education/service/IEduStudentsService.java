package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduStudents;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.domain.vo.EduStudentSelectTeacherVo;

/**
 * 学生信息Service接口
 *
 * @author xjk
 * @date 2025-01-22
 */
public interface IEduStudentsService
{
    /**
     * 查询学生信息
     *
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    public EduStudents selectEduStudentsByStudentId(Long studentId);

    /**
     * 查询学生信息列表
     *
     * @param eduStudents 学生信息
     * @return 学生信息集合
     */
    public List<EduStudents> selectEduStudentsList(EduStudents eduStudents);

    public List<EduStudentSelectTeacherVo> studentSelectTeachers(EduTeachers eduTeachers);

    /**
     * 新增学生信息
     *
     * @param eduStudents 学生信息
     * @return 结果
     */
    public int insertEduStudents(EduStudents eduStudents);

    /**
     * 修改学生信息
     *
     * @param eduStudents 学生信息
     * @return 结果
     */
    public int updateEduStudents(EduStudents eduStudents);

    /**
     * 批量删除学生信息
     *
     * @param studentIds 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteEduStudentsByStudentIds(Long[] studentIds);

    /**
     * 删除学生信息信息
     *
     * @param studentId 学生信息主键
     * @return 结果
     */
    public int deleteEduStudentsByStudentId(Long studentId);
}

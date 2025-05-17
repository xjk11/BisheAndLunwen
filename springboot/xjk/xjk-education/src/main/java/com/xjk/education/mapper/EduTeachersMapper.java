package com.xjk.education.mapper;

import java.util.List;

import com.xjk.education.domain.EduStudents;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.domain.vo.EduTeacherSelectStudentVo;

/**
 * 教师信息Mapper接口
 *
 * @author xjk
 * @date 2025-01-22
 */
public interface EduTeachersMapper
{
    /**
     * 查询教师信息
     *
     * @param teacherId 教师信息主键
     * @return 教师信息
     */
    public EduTeachers selectEduTeachersByTeacherId(Long teacherId);

    /**
     * 查询教师信息列表
     *
     * @param eduTeachers 教师信息
     * @return 教师信息集合
     */
    public List<EduTeachers> selectEduTeachersList(EduTeachers eduTeachers);

    public List<EduTeacherSelectStudentVo> teacherSelectStudent(EduStudents eduStudents);

    public Long selectTeacherIdByUserId(Long userId);

    /**
     * 新增教师信息
     *
     * @param eduTeachers 教师信息
     * @return 结果
     */
    public int insertEduTeachers(EduTeachers eduTeachers);

    /**
     * 修改教师信息
     *
     * @param eduTeachers 教师信息
     * @return 结果
     */
    public int updateEduTeachers(EduTeachers eduTeachers);

    /**
     * 删除教师信息
     *
     * @param teacherId 教师信息主键
     * @return 结果
     */
    public int deleteEduTeachersByTeacherId(Long teacherId);

    /**
     * 批量删除教师信息
     *
     * @param teacherIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduTeachersByTeacherIds(Long[] teacherIds);

    public int importInsertEduTeachers(List<EduTeachers> eduTeachersList);
}

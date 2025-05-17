package com.xjk.education.service.impl;

import java.util.List;
import com.xjk.common.utils.DateUtils;
import com.xjk.education.domain.EduStudents;
import com.xjk.education.domain.vo.EduTeacherSelectStudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduTeachersMapper;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.service.IEduTeachersService;

/**
 * 教师信息Service业务层处理
 *
 * @author xjk
 * @date 2025-01-22
 */
@Service
public class EduTeachersServiceImpl implements IEduTeachersService
{
    @Autowired
    private EduTeachersMapper eduTeachersMapper;

    /**
     * 查询教师信息
     *
     * @param teacherId 教师信息主键
     * @return 教师信息
     */
    @Override
    public EduTeachers selectEduTeachersByTeacherId(Long teacherId)
    {
        return eduTeachersMapper.selectEduTeachersByTeacherId(teacherId);
    }

    @Override
    public Long selectTeacherIdByUserId(Long userId)
    {
        return eduTeachersMapper.selectTeacherIdByUserId(userId);
    }

    /**
     * 查询教师信息列表
     *
     * @param eduTeachers 教师信息
     * @return 教师信息
     */
    @Override
    public List<EduTeachers> selectEduTeachersList(EduTeachers eduTeachers)
    {
        return eduTeachersMapper.selectEduTeachersList(eduTeachers);
    }

    @Override
    public List<EduTeacherSelectStudentVo> teacherSelectStudent(EduStudents eduStudents)
    {
        return eduTeachersMapper.teacherSelectStudent(eduStudents);
    }

    /**
     * 新增教师信息
     *
     * @param eduTeachers 教师信息
     * @return 结果
     */
    @Override
    public int insertEduTeachers(EduTeachers eduTeachers)
    {
        eduTeachers.setCreateTime(DateUtils.getNowDate());
        return eduTeachersMapper.insertEduTeachers(eduTeachers);
    }

    /**
     * 修改教师信息
     *
     * @param eduTeachers 教师信息
     * @return 结果
     */
    @Override
    public int updateEduTeachers(EduTeachers eduTeachers)
    {
        eduTeachers.setUpdateTime(DateUtils.getNowDate());
        return eduTeachersMapper.updateEduTeachers(eduTeachers);
    }

    /**
     * 批量删除教师信息
     *
     * @param teacherIds 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public int deleteEduTeachersByTeacherIds(Long[] teacherIds)
    {
        return eduTeachersMapper.deleteEduTeachersByTeacherIds(teacherIds);
    }

    /**
     * 删除教师信息信息
     *
     * @param teacherId 教师信息主键
     * @return 结果
     */
    @Override
    public int deleteEduTeachersByTeacherId(Long teacherId)
    {
        return eduTeachersMapper.deleteEduTeachersByTeacherId(teacherId);
    }

    @Override
    public int importInsertEduTeachers(List<EduTeachers> eduTeachersList)
    {
        return eduTeachersMapper.importInsertEduTeachers(eduTeachersList);
    }
}

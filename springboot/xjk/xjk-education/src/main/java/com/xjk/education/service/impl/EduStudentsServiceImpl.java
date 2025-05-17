package com.xjk.education.service.impl;

import java.util.List;
import com.xjk.common.utils.DateUtils;
import com.xjk.education.domain.EduTeachers;
import com.xjk.education.domain.vo.EduStudentSelectTeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduStudentsMapper;
import com.xjk.education.domain.EduStudents;
import com.xjk.education.service.IEduStudentsService;

/**
 * 学生信息Service业务层处理
 *
 * @author xjk
 * @date 2025-01-22
 */
@Service
public class EduStudentsServiceImpl implements IEduStudentsService
{
    @Autowired
    private EduStudentsMapper eduStudentsMapper;

    /**
     * 查询学生信息
     *
     * @param studentId 学生信息主键
     * @return 学生信息
     */
    @Override
    public EduStudents selectEduStudentsByStudentId(Long studentId)
    {
        return eduStudentsMapper.selectEduStudentsByStudentId(studentId);
    }

    /**
     * 查询学生信息列表
     *
     * @param eduStudents 学生信息
     * @return 学生信息
     */
    @Override
    public List<EduStudents> selectEduStudentsList(EduStudents eduStudents)
    {
        return eduStudentsMapper.selectEduStudentsList(eduStudents);
    }

    @Override
    public List<EduStudentSelectTeacherVo> studentSelectTeachers(EduTeachers eduTeachers)
    {
        return eduStudentsMapper.studentSelectTeachers(eduTeachers);
    }

    /**
     * 新增学生信息
     *
     * @param eduStudents 学生信息
     * @return 结果
     */
    @Override
    public int insertEduStudents(EduStudents eduStudents)
    {
        eduStudents.setCreateTime(DateUtils.getNowDate());
        return eduStudentsMapper.insertEduStudents(eduStudents);
    }

    /**
     * 修改学生信息
     *
     * @param eduStudents 学生信息
     * @return 结果
     */
    @Override
    public int updateEduStudents(EduStudents eduStudents)
    {
        eduStudents.setUpdateTime(DateUtils.getNowDate());
        return eduStudentsMapper.updateEduStudents(eduStudents);
    }

    /**
     * 批量删除学生信息
     *
     * @param studentIds 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentsByStudentIds(Long[] studentIds)
    {
        return eduStudentsMapper.deleteEduStudentsByStudentIds(studentIds);
    }

    /**
     * 删除学生信息信息
     *
     * @param studentId 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteEduStudentsByStudentId(Long studentId)
    {
        return eduStudentsMapper.deleteEduStudentsByStudentId(studentId);
    }
}

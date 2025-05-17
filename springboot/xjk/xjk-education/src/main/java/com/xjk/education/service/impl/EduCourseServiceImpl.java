package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduCourseScheduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduCourseMapper;
import com.xjk.education.domain.EduCourse;
import com.xjk.education.service.IEduCourseService;

/**
 * 课程管理Service业务层处理
 *
 * @author xjk
 * @date 2025-01-23
 */
@Service
public class EduCourseServiceImpl implements IEduCourseService
{
    @Autowired
    private EduCourseMapper eduCourseMapper;

    /**
     * 查询课程管理
     *
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    @Override
    public EduCourse selectEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.selectEduCourseByCourseId(courseId);
    }

    /**
     * 查询课程管理列表
     *
     * @param eduCourse 课程管理
     * @return 课程管理
     */
    @Override
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse)
    {
        return eduCourseMapper.selectEduCourseList(eduCourse);
    }

    @Override
    public List<EduCourseScheduleVo> selectEduCourseScheduleList(EduCourse eduCourse) {
        return eduCourseMapper.selectEduCourseScheduleList(eduCourse);
    }

    /**
     * 新增课程管理
     *
     * @param eduCourse 课程管理
     * @return 结果
     */
    @Override
    public int insertEduCourse(EduCourse eduCourse)
    {
        return eduCourseMapper.insertEduCourse(eduCourse);
    }

    /**
     * 修改课程管理
     *
     * @param eduCourse 课程管理
     * @return 结果
     */
    @Override
    public int updateEduCourse(EduCourse eduCourse)
    {
        return eduCourseMapper.updateEduCourse(eduCourse);
    }

    /**
     * 批量删除课程管理
     *
     * @param courseIds 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseIds(Long[] courseIds)
    {
        return eduCourseMapper.deleteEduCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程管理信息
     *
     * @param courseId 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteEduCourseByCourseId(Long courseId)
    {
        return eduCourseMapper.deleteEduCourseByCourseId(courseId);
    }
}

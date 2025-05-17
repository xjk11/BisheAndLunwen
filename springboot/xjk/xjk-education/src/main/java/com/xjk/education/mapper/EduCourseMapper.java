package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduCourse;
import com.xjk.education.domain.vo.EduCourseScheduleVo;

/**
 * 课程管理Mapper接口
 *
 * @author xjk
 * @date 2025-01-23
 */
public interface EduCourseMapper
{
    /**
     * 查询课程管理
     *
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    public EduCourse selectEduCourseByCourseId(Long courseId);

    /**
     * 查询课程管理列表
     *
     * @param eduCourse 课程管理
     * @return 课程管理集合
     */
    public List<EduCourse> selectEduCourseList(EduCourse eduCourse);


    /**
     * 查询个人课程列表
     *
     * @param eduCourse 课程管理
     * @return 课程管理集合
     */
    public List<EduCourseScheduleVo> selectEduCourseScheduleList(EduCourse eduCourse);

    /**
     * 新增课程管理
     *
     * @param eduCourse 课程管理
     * @return 结果
     */
    public int insertEduCourse(EduCourse eduCourse);

    /**
     * 修改课程管理
     *
     * @param eduCourse 课程管理
     * @return 结果
     */
    public int updateEduCourse(EduCourse eduCourse);

    /**
     * 删除课程管理
     *
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteEduCourseByCourseId(Long courseId);

    /**
     * 批量删除课程管理
     *
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduCourseByCourseIds(Long[] courseIds);
}

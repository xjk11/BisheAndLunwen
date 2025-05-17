package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduSelectcoursesMapper;
import com.xjk.education.mapper.EduUserselectcoursesMapper;
import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.service.IEduSelectcoursesService;

/**
 * 学生选课Service业务层处理
 *
 * @author xjk
 * @date 2025-03-04
 */
@Service
public class EduSelectcoursesServiceImpl implements IEduSelectcoursesService
{
    @Autowired
    private EduSelectcoursesMapper eduSelectcoursesMapper;

    @Autowired
    private EduUserselectcoursesMapper eduUserselectcoursesMapper;

    /**
     * 查询学生选课
     *
     * @param selectId 学生选课主键
     * @return 学生选课
     */
    @Override
    public EduSelectcourses selectEduSelectcoursesBySelectId(Long selectId)
    {
        return eduSelectcoursesMapper.selectEduSelectcoursesBySelectId(selectId);
    }

    /**
     * 查询学生选课列表
     *
     * @param eduSelectcourses 学生选课
     * @return 学生选课
     */
    @Override
    public List<EduSelectcourses> selectEduSelectcoursesList(EduSelectcourses eduSelectcourses)
    {
        return eduSelectcoursesMapper.selectEduSelectcoursesList(eduSelectcourses);
    }

    @Override
    public List<EduSelectcourseVo> selectEduSelectcoursesVoList(EduSelectcourses eduSelectcourses)
    {
        return eduSelectcoursesMapper.selectEduSelectcoursesVoList(eduSelectcourses);
    }

    /**
     * 新增学生选课
     *
     * @param eduSelectcourses 学生选课
     * @return 结果
     */
    @Override
    public int insertEduSelectcourses(EduSelectcourses eduSelectcourses)
    {
        return eduSelectcoursesMapper.insertEduSelectcourses(eduSelectcourses);
    }

    /**
     * 修改学生选课
     *
     * @param eduSelectcourses 学生选课
     * @return 结果
     */
    @Override
    public int updateEduSelectcourses(EduSelectcourses eduSelectcourses)
    {
        return eduSelectcoursesMapper.updateEduSelectcourses(eduSelectcourses);
    }

    @Override
    public int isCourseSelectedByUser(@Param("userId") Long userId, @Param("selectId") Long selectId) {
        return eduSelectcoursesMapper.checkIfUserSelectedCourse(userId, selectId);
    }

    @Override
    public int getCourseRemainingCapacity(Long selectId) {
        return eduSelectcoursesMapper.checkCourseRemainingCapacity(selectId);
    }

    /**
     * 批量删除学生选课
     *
     * @param selectIds 需要删除的学生选课主键
     * @return 结果
     */
    @Override
    public int deleteEduSelectcoursesBySelectIds(Long[] selectIds)
    {
        return eduSelectcoursesMapper.deleteEduSelectcoursesBySelectIds(selectIds);
    }

    /**
     * 删除学生选课信息
     *
     * @param selectId 学生选课主键
     * @return 结果
     */
    @Override
    public int deleteEduSelectcoursesBySelectId(Long selectId)
    {
        return eduSelectcoursesMapper.deleteEduSelectcoursesBySelectId(selectId);
    }
}

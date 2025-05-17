package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduUserselectcoursesMapper;
import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.service.IEduUserselectcoursesService;

/**
 * 个人选课信息查询Service业务层处理
 *
 * @author xjk
 * @date 2025-03-04
 */
@Service
public class EduUserselectcoursesServiceImpl implements IEduUserselectcoursesService
{
    @Autowired
    private EduUserselectcoursesMapper eduUserselectcoursesMapper;

    /**
     * 查询个人选课信息查询
     *
     * @param userSelectId 个人选课信息查询主键
     * @return 个人选课信息查询
     */
    @Override
    public EduUserselectcourses selectEduUserselectcoursesByUserSelectId(Long userSelectId)
    {
        return eduUserselectcoursesMapper.selectEduUserselectcoursesByUserSelectId(userSelectId);
    }

    /**
     * 查询个人选课信息查询列表
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 个人选课信息查询
     */
    @Override
    public List<EduUserselectcourses> selectEduUserselectcoursesList(EduUserselectcourses eduUserselectcourses)
    {
        return eduUserselectcoursesMapper.selectEduUserselectcoursesList(eduUserselectcourses);
    }

    @Override
    public List<EduSelectcourseVo> selectEduUserSelectcoursesVoList(EduSelectcourses eduSelectcourses)
    {
        return eduUserselectcoursesMapper.selectEduUserSelectcoursesVoList(eduSelectcourses);
    }

    /**
     * 新增个人选课信息查询
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 结果
     */
    @Override
    public int insertEduUserselectcourses(EduUserselectcourses eduUserselectcourses)
    {
        return eduUserselectcoursesMapper.insertEduUserselectcourses(eduUserselectcourses);
    }

    /**
     * 修改个人选课信息查询
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 结果
     */
    @Override
    public int updateEduUserselectcourses(EduUserselectcourses eduUserselectcourses)
    {
        return eduUserselectcoursesMapper.updateEduUserselectcourses(eduUserselectcourses);
    }

    /**
     * 批量删除个人选课信息查询
     *
     * @param userSelectIds 需要删除的个人选课信息查询主键
     * @return 结果
     */
    @Override
    public int deleteEduUserselectcoursesByUserSelectIds(Long[] userSelectIds)
    {
        return eduUserselectcoursesMapper.deleteEduUserselectcoursesByUserSelectIds(userSelectIds);
    }

    /**
     * 删除个人选课信息查询信息
     *
     * @param userSelectId 个人选课信息查询主键
     * @return 结果
     */
    @Override
    public int deleteEduUserselectcoursesByUserSelectId(Long userSelectId)
    {
        return eduUserselectcoursesMapper.deleteEduUserselectcoursesByUserSelectId(userSelectId);
    }
}

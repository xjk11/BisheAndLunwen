package com.xjk.education.mapper;

import java.util.List;

import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;

/**
 * 个人选课信息查询Mapper接口
 *
 * @author xjk
 * @date 2025-03-04
 */
public interface EduUserselectcoursesMapper
{
    /**
     * 查询个人选课信息查询
     *
     * @param userSelectId 个人选课信息查询主键
     * @return 个人选课信息查询
     */
    public EduUserselectcourses selectEduUserselectcoursesByUserSelectId(Long userSelectId);

    /**
     * 查询个人选课信息查询列表
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 个人选课信息查询集合
     */
    public List<EduUserselectcourses> selectEduUserselectcoursesList(EduUserselectcourses eduUserselectcourses);

    public List<EduSelectcourseVo> selectEduUserSelectcoursesVoList(EduSelectcourses eduSelectcourses);

    /**
     * 新增个人选课信息查询
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 结果
     */
    public int insertEduUserselectcourses(EduUserselectcourses eduUserselectcourses);

    /**
     * 修改个人选课信息查询
     *
     * @param eduUserselectcourses 个人选课信息查询
     * @return 结果
     */
    public int updateEduUserselectcourses(EduUserselectcourses eduUserselectcourses);

    /**
     * 删除个人选课信息查询
     *
     * @param userSelectId 个人选课信息查询主键
     * @return 结果
     */
    public int deleteEduUserselectcoursesByUserSelectId(Long userSelectId);

    /**
     * 批量删除个人选课信息查询
     *
     * @param userSelectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduUserselectcoursesByUserSelectIds(Long[] userSelectIds);
}

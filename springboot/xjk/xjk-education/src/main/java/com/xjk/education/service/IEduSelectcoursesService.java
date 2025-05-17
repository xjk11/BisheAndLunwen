package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.domain.EduUserselectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import org.apache.ibatis.annotations.Param;

/**
 * 学生选课Service接口
 *
 * @author xjk
 * @date 2025-03-04
 */
public interface IEduSelectcoursesService
{
    /**
     * 查询学生选课
     *
     * @param selectId 学生选课主键
     * @return 学生选课
     */
    public EduSelectcourses selectEduSelectcoursesBySelectId(Long selectId);

    /**
     * 查询学生选课列表
     *
     * @param eduSelectcourses 学生选课
     * @return 学生选课集合
     */
    public List<EduSelectcourses> selectEduSelectcoursesList(EduSelectcourses eduSelectcourses);

    public List<EduSelectcourseVo> selectEduSelectcoursesVoList(EduSelectcourses eduSelectcourses);

    /**
     * 新增学生选课
     *
     * @param eduSelectcourses 学生选课
     * @return 结果
     */
    public int insertEduSelectcourses(EduSelectcourses eduSelectcourses);

    /**
     * 判断用户是否已经选择该课程
     *
     * @param userId 用户ID
     * @param selectId 课程ID
     * @return 1表示已选择，0表示未选择
     */
    int isCourseSelectedByUser(@Param("userId") Long userId, @Param("selectId") Long selectId);

    /**
     * 获取课程剩余容量
     *
     * @param selectId 课程ID
     * @return 课程剩余容量
     */
    public int getCourseRemainingCapacity(Long selectId);

    /**
     * 修改学生选课
     *
     * @param eduSelectcourses 学生选课
     * @return 结果
     */
    public int updateEduSelectcourses(EduSelectcourses eduSelectcourses);

    /**
     * 批量删除学生选课
     *
     * @param selectIds 需要删除的学生选课主键集合
     * @return 结果
     */
    public int deleteEduSelectcoursesBySelectIds(Long[] selectIds);

    /**
     * 删除学生选课信息
     *
     * @param selectId 学生选课主键
     * @return 结果
     */
    public int deleteEduSelectcoursesBySelectId(Long selectId);

}

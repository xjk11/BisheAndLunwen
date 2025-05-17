package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduSelectcourses;
import com.xjk.education.domain.vo.EduSelectcourseVo;
import org.apache.ibatis.annotations.Param;

/**
 * 学生选课Mapper接口
 *
 * @author xjk
 * @date 2025-03-04
 */
public interface EduSelectcoursesMapper
{
    /**
     * 查询学生选课
     *
     * @param selectId 学生选课主键
     * @return 学生选课
     */
    public EduSelectcourses selectEduSelectcoursesBySelectId(Long selectId);

    // 查询课程剩余
    public int checkCourseRemainingCapacity(Long selectId);

    // 查询用户是否选过该课程
    public int checkIfUserSelectedCourse(@Param("userId") Long userId, @Param("selectId") Long selectId);

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
     * 修改学生选课
     *
     * @param eduSelectcourses 学生选课
     * @return 结果
     */
    public int updateEduSelectcourses(EduSelectcourses eduSelectcourses);

    /**
     * 删除学生选课
     *
     * @param selectId 学生选课主键
     * @return 结果
     */
    public int deleteEduSelectcoursesBySelectId(Long selectId);

    /**
     * 批量删除学生选课
     *
     * @param selectIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduSelectcoursesBySelectIds(Long[] selectIds);
}

package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduSchedule;

/**
 * 个人课程Service接口
 * 
 * @author xjk
 * @date 2025-01-23
 */
public interface IEduScheduleService 
{
    /**
     * 查询个人课程
     * 
     * @param scheduleId 个人课程主键
     * @return 个人课程
     */
    public EduSchedule selectEduScheduleByScheduleId(Long scheduleId);

    /**
     * 查询个人课程列表
     * 
     * @param eduSchedule 个人课程
     * @return 个人课程集合
     */
    public List<EduSchedule> selectEduScheduleList(EduSchedule eduSchedule);

    /**
     * 新增个人课程
     * 
     * @param eduSchedule 个人课程
     * @return 结果
     */
    public int insertEduSchedule(EduSchedule eduSchedule);

    /**
     * 修改个人课程
     * 
     * @param eduSchedule 个人课程
     * @return 结果
     */
    public int updateEduSchedule(EduSchedule eduSchedule);

    /**
     * 批量删除个人课程
     * 
     * @param scheduleIds 需要删除的个人课程主键集合
     * @return 结果
     */
    public int deleteEduScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除个人课程信息
     * 
     * @param scheduleId 个人课程主键
     * @return 结果
     */
    public int deleteEduScheduleByScheduleId(Long scheduleId);
}

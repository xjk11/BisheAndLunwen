package com.xjk.education.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduScheduleMapper;
import com.xjk.education.domain.EduSchedule;
import com.xjk.education.service.IEduScheduleService;

/**
 * 个人课程Service业务层处理
 * 
 * @author xjk
 * @date 2025-01-23
 */
@Service
public class EduScheduleServiceImpl implements IEduScheduleService 
{
    @Autowired
    private EduScheduleMapper eduScheduleMapper;

    /**
     * 查询个人课程
     * 
     * @param scheduleId 个人课程主键
     * @return 个人课程
     */
    @Override
    public EduSchedule selectEduScheduleByScheduleId(Long scheduleId)
    {
        return eduScheduleMapper.selectEduScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询个人课程列表
     * 
     * @param eduSchedule 个人课程
     * @return 个人课程
     */
    @Override
    public List<EduSchedule> selectEduScheduleList(EduSchedule eduSchedule)
    {
        return eduScheduleMapper.selectEduScheduleList(eduSchedule);
    }

    /**
     * 新增个人课程
     * 
     * @param eduSchedule 个人课程
     * @return 结果
     */
    @Override
    public int insertEduSchedule(EduSchedule eduSchedule)
    {
        return eduScheduleMapper.insertEduSchedule(eduSchedule);
    }

    /**
     * 修改个人课程
     * 
     * @param eduSchedule 个人课程
     * @return 结果
     */
    @Override
    public int updateEduSchedule(EduSchedule eduSchedule)
    {
        return eduScheduleMapper.updateEduSchedule(eduSchedule);
    }

    /**
     * 批量删除个人课程
     * 
     * @param scheduleIds 需要删除的个人课程主键
     * @return 结果
     */
    @Override
    public int deleteEduScheduleByScheduleIds(Long[] scheduleIds)
    {
        return eduScheduleMapper.deleteEduScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除个人课程信息
     * 
     * @param scheduleId 个人课程主键
     * @return 结果
     */
    @Override
    public int deleteEduScheduleByScheduleId(Long scheduleId)
    {
        return eduScheduleMapper.deleteEduScheduleByScheduleId(scheduleId);
    }
}

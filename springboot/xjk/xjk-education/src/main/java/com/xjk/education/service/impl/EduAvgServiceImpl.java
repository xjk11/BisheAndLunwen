package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduAvgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduAvgMapper;
import com.xjk.education.domain.EduAvg;
import com.xjk.education.service.IEduAvgService;

/**
 * 班级平均成绩Service业务层处理
 *
 * @author xjk
 * @date 2025-02-03
 */
@Service
public class EduAvgServiceImpl implements IEduAvgService
{
    @Autowired
    private EduAvgMapper eduAvgMapper;

    /**
     * 查询班级平均成绩
     *
     * @param avgId 班级平均成绩主键
     * @return 班级平均成绩
     */
    @Override
    public EduAvg selectEduAvgByAvgId(Long avgId)
    {
        return eduAvgMapper.selectEduAvgByAvgId(avgId);
    }

    /**
     * 查询班级平均成绩列表
     *
     * @param eduAvg 班级平均成绩
     * @return 班级平均成绩
     */
    @Override
    public List<EduAvg> selectEduAvgList(EduAvg eduAvg)
    {
        return eduAvgMapper.selectEduAvgList(eduAvg);
    }

    // 新增的方法
    @Override
    public List<EduAvgVo> selectAverageScoreByClassCourseTeacher(EduAvg eduAvg) {
        return eduAvgMapper.selectAverageScoreByClassCourseTeacher(eduAvg);
    }

    /**
     * 新增班级平均成绩
     *
     * @param eduAvg 班级平均成绩
     * @return 结果
     */
    @Override
    public int insertEduAvg(EduAvg eduAvg)
    {
        return eduAvgMapper.insertEduAvg(eduAvg);
    }

    /**
     * 修改班级平均成绩
     *
     * @param eduAvg 班级平均成绩
     * @return 结果
     */
    @Override
    public int updateEduAvg(EduAvg eduAvg)
    {
        return eduAvgMapper.updateEduAvg(eduAvg);
    }

    /**
     * 批量删除班级平均成绩
     *
     * @param avgIds 需要删除的班级平均成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduAvgByAvgIds(Long[] avgIds)
    {
        return eduAvgMapper.deleteEduAvgByAvgIds(avgIds);
    }

    /**
     * 删除班级平均成绩信息
     *
     * @param avgId 班级平均成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduAvgByAvgId(Long avgId)
    {
        return eduAvgMapper.deleteEduAvgByAvgId(avgId);
    }
}

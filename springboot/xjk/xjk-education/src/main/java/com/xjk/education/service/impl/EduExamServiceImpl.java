package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduExamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduExamMapper;
import com.xjk.education.domain.EduExam;
import com.xjk.education.service.IEduExamService;

/**
 * 考试信息Service业务层处理
 *
 * @author xjk
 * @date 2025-02-07
 */
@Service
public class EduExamServiceImpl implements IEduExamService
{
    @Autowired
    private EduExamMapper eduExamMapper;

    /**
     * 查询考试信息
     *
     * @param examId 考试信息主键
     * @return 考试信息
     */
    @Override
    public EduExam selectEduExamByExamId(Long examId)
    {
        return eduExamMapper.selectEduExamByExamId(examId);
    }

    /**
     * 查询考试信息列表
     *
     * @param eduExam 考试信息
     * @return 考试信息
     */
    @Override
    public List<EduExam> selectEduExamList(EduExam eduExam)
    {
        return eduExamMapper.selectEduExamList(eduExam);
    }

    @Override
    public List<EduExamVo> selectEduExamVoList(EduExam eduExam)
    {
        return eduExamMapper.selectEduExamVoList(eduExam);
    }

    /**
     * 新增考试信息
     *
     * @param eduExam 考试信息
     * @return 结果
     */
    @Override
    public int insertEduExam(EduExam eduExam)
    {
        return eduExamMapper.insertEduExam(eduExam);
    }

    /**
     * 修改考试信息
     *
     * @param eduExam 考试信息
     * @return 结果
     */
    @Override
    public int updateEduExam(EduExam eduExam)
    {
        return eduExamMapper.updateEduExam(eduExam);
    }

    /**
     * 批量删除考试信息
     *
     * @param examIds 需要删除的考试信息主键
     * @return 结果
     */
    @Override
    public int deleteEduExamByExamIds(Long[] examIds)
    {
        return eduExamMapper.deleteEduExamByExamIds(examIds);
    }

    /**
     * 删除考试信息信息
     *
     * @param examId 考试信息主键
     * @return 结果
     */
    @Override
    public int deleteEduExamByExamId(Long examId)
    {
        return eduExamMapper.deleteEduExamByExamId(examId);
    }
}

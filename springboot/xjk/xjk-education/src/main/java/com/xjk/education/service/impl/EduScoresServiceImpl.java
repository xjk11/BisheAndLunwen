package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduScoresDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduScoresMapper;
import com.xjk.education.domain.EduScores;
import com.xjk.education.service.IEduScoresService;

/**
 * 学生成绩Service业务层处理
 *
 * @author xjk
 * @date 2025-02-02
 */
@Service
public class EduScoresServiceImpl implements IEduScoresService
{
    @Autowired
    private EduScoresMapper eduScoresMapper;

    /**
     * 查询学生成绩
     *
     * @param scoreId 学生成绩主键
     * @return 学生成绩
     */
    @Override
    public EduScores selectEduScoresByScoreId(Long scoreId)
    {
        return eduScoresMapper.selectEduScoresByScoreId(scoreId);
    }

    /**
     * 查询学生成绩列表
     *
     * @param eduScores 学生成绩
     * @return 学生成绩
     */
    @Override
    public List<EduScores> selectEduScoresList(EduScores eduScores)
    {
        return eduScoresMapper.selectEduScoresList(eduScores);
    }

    @Override
    public List<EduScoresDetailVo> getEduScoresDetails(EduScores eduScores) {
        return eduScoresMapper.getEduScoresDetails(eduScores);
    }

    /**
     * 新增学生成绩
     *
     * @param eduScores 学生成绩
     * @return 结果
     */
    @Override
    public int insertEduScores(EduScores eduScores)
    {
        return eduScoresMapper.insertEduScores(eduScores);
    }

    /**
     * 修改学生成绩
     *
     * @param eduScores 学生成绩
     * @return 结果
     */
    @Override
    public int updateEduScores(EduScores eduScores)
    {
        return eduScoresMapper.updateEduScores(eduScores);
    }

    /**
     * 批量删除学生成绩
     *
     * @param scoreIds 需要删除的学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduScoresByScoreIds(Long[] scoreIds)
    {
        return eduScoresMapper.deleteEduScoresByScoreIds(scoreIds);
    }

    /**
     * 删除学生成绩信息
     *
     * @param scoreId 学生成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduScoresByScoreId(Long scoreId)
    {
        return eduScoresMapper.deleteEduScoresByScoreId(scoreId);
    }

    @Override
    public int importInsertEduScores(List<EduScores> eduScoresList)
    {
        return eduScoresMapper.importInsertEduScores(eduScoresList);
    }
}

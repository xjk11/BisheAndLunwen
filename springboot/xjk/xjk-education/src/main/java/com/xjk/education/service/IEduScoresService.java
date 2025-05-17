package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduScores;
import com.xjk.education.domain.vo.EduScoresDetailVo;

/**
 * 学生成绩Service接口
 *
 * @author xjk
 * @date 2025-02-02
 */
public interface IEduScoresService
{
    /**
     * 查询学生成绩
     *
     * @param scoreId 学生成绩主键
     * @return 学生成绩
     */
    public EduScores selectEduScoresByScoreId(Long scoreId);

    /**
     * 查询学生成绩列表
     *
     * @param eduScores 学生成绩
     * @return 学生成绩集合
     */
    public List<EduScores> selectEduScoresList(EduScores eduScores);


    /**
     * 根据条件查询复杂查询结果
     *
     * @param eduScores 查询条件
     * @return 复杂查询结果集合
     */
    public List<EduScoresDetailVo> getEduScoresDetails(EduScores eduScores);

    /**
     * 新增学生成绩
     *
     * @param eduScores 学生成绩
     * @return 结果
     */
    public int insertEduScores(EduScores eduScores);

    /**
     * 修改学生成绩
     *
     * @param eduScores 学生成绩
     * @return 结果
     */
    public int updateEduScores(EduScores eduScores);

    /**
     * 批量删除学生成绩
     *
     * @param scoreIds 需要删除的学生成绩主键集合
     * @return 结果
     */
    public int deleteEduScoresByScoreIds(Long[] scoreIds);

    /**
     * 删除学生成绩信息
     *
     * @param scoreId 学生成绩主键
     * @return 结果
     */
    public int deleteEduScoresByScoreId(Long scoreId);

    /**
     * 批量新增学生成绩用于导入
     *
     * @param eduScoresList 需要新增的数据主键集合
     * @return 影响行数
     */
    public int importInsertEduScores(List<EduScores> eduScoresList);
}

package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduScores;
import com.xjk.education.domain.vo.EduScoresDetailVo;

/**
 * 学生成绩Mapper接口
 *
 * @author xjk
 * @date 2025-02-02
 */
public interface EduScoresMapper
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
     * 删除学生成绩
     *
     * @param scoreId 学生成绩主键
     * @return 结果
     */
    public int deleteEduScoresByScoreId(Long scoreId);

    /**
     * 批量删除学生成绩
     *
     * @param scoreIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduScoresByScoreIds(Long[] scoreIds);

    /**
     * 批量新增学生成绩用于导入
     *
     * @param eduScoresList 需要新增的数据主键集合
     * @return 影响行数
     */
    public int importInsertEduScores(List<EduScores> eduScoresList);
}

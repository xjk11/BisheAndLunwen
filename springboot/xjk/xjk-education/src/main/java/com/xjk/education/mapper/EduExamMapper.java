package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduExam;
import com.xjk.education.domain.vo.EduExamVo;

/**
 * 考试信息Mapper接口
 *
 * @author xjk
 * @date 2025-02-07
 */
public interface EduExamMapper
{
    /**
     * 查询考试信息
     *
     * @param examId 考试信息主键
     * @return 考试信息
     */
    public EduExam selectEduExamByExamId(Long examId);

    /**
     * 查询考试信息列表
     *
     * @param eduExam 考试信息
     * @return 考试信息集合
     */
    public List<EduExam> selectEduExamList(EduExam eduExam);

    public List<EduExamVo> selectEduExamVoList(EduExam eduExam);

    /**
     * 新增考试信息
     *
     * @param eduExam 考试信息
     * @return 结果
     */
    public int insertEduExam(EduExam eduExam);

    /**
     * 修改考试信息
     *
     * @param eduExam 考试信息
     * @return 结果
     */
    public int updateEduExam(EduExam eduExam);

    /**
     * 删除考试信息
     *
     * @param examId 考试信息主键
     * @return 结果
     */
    public int deleteEduExamByExamId(Long examId);

    /**
     * 批量删除考试信息
     *
     * @param examIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduExamByExamIds(Long[] examIds);
}

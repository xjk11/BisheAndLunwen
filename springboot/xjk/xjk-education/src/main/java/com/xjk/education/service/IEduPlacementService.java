package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduPlacement;
import com.xjk.education.domain.vo.EduPlacementVo;

/**
 * 等级考试成绩Service接口
 *
 * @author xjk
 * @date 2025-02-03
 */
public interface IEduPlacementService
{
    /**
     * 查询等级考试成绩
     *
     * @param gradeId 等级考试成绩主键
     * @return 等级考试成绩
     */
    public EduPlacement selectEduPlacementByGradeId(Long gradeId);

    /**
     * 查询等级考试成绩列表
     *
     * @param eduPlacement 等级考试成绩
     * @return 等级考试成绩集合
     */
    public List<EduPlacement> selectEduPlacementList(EduPlacement eduPlacement);

    public List<EduPlacementVo> selectEduUserPlacementList(EduPlacement eduPlacement);

    /**
     * 新增等级考试成绩
     *
     * @param eduPlacement 等级考试成绩
     * @return 结果
     */
    public int insertEduPlacement(EduPlacement eduPlacement);

    /**
     * 修改等级考试成绩
     *
     * @param eduPlacement 等级考试成绩
     * @return 结果
     */
    public int updateEduPlacement(EduPlacement eduPlacement);

    /**
     * 批量删除等级考试成绩
     *
     * @param gradeIds 需要删除的等级考试成绩主键集合
     * @return 结果
     */
    public int deleteEduPlacementByGradeIds(Long[] gradeIds);

    /**
     * 删除等级考试成绩信息
     *
     * @param gradeId 等级考试成绩主键
     * @return 结果
     */
    public int deleteEduPlacementByGradeId(Long gradeId);

    public int importInsertEduPlacement(List<EduPlacement> eduPlacementList);
}

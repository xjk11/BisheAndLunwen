package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduPlacementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduPlacementMapper;
import com.xjk.education.domain.EduPlacement;
import com.xjk.education.service.IEduPlacementService;

/**
 * 等级考试成绩Service业务层处理
 *
 * @author xjk
 * @date 2025-02-03
 */
@Service
public class EduPlacementServiceImpl implements IEduPlacementService
{
    @Autowired
    private EduPlacementMapper eduPlacementMapper;

    /**
     * 查询等级考试成绩
     *
     * @param gradeId 等级考试成绩主键
     * @return 等级考试成绩
     */
    @Override
    public EduPlacement selectEduPlacementByGradeId(Long gradeId)
    {
        return eduPlacementMapper.selectEduPlacementByGradeId(gradeId);
    }

    /**
     * 查询等级考试成绩列表
     *
     * @param eduPlacement 等级考试成绩
     * @return 等级考试成绩
     */
    @Override
    public List<EduPlacement> selectEduPlacementList(EduPlacement eduPlacement)
    {
        return eduPlacementMapper.selectEduPlacementList(eduPlacement);
    }

    @Override
    public List<EduPlacementVo> selectEduUserPlacementList(EduPlacement eduPlacement)
    {
        return eduPlacementMapper.selectEduUserPlacementList(eduPlacement);
    }

    /**
     * 新增等级考试成绩
     *
     * @param eduPlacement 等级考试成绩
     * @return 结果
     */
    @Override
    public int insertEduPlacement(EduPlacement eduPlacement)
    {
        return eduPlacementMapper.insertEduPlacement(eduPlacement);
    }

    /**
     * 修改等级考试成绩
     *
     * @param eduPlacement 等级考试成绩
     * @return 结果
     */
    @Override
    public int updateEduPlacement(EduPlacement eduPlacement)
    {
        return eduPlacementMapper.updateEduPlacement(eduPlacement);
    }

    /**
     * 批量删除等级考试成绩
     *
     * @param gradeIds 需要删除的等级考试成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduPlacementByGradeIds(Long[] gradeIds)
    {
        return eduPlacementMapper.deleteEduPlacementByGradeIds(gradeIds);
    }

    /**
     * 删除等级考试成绩信息
     *
     * @param gradeId 等级考试成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduPlacementByGradeId(Long gradeId)
    {
        return eduPlacementMapper.deleteEduPlacementByGradeId(gradeId);
    }

    @Override
    public int importInsertEduPlacement(List<EduPlacement> eduPlacementList)
    {
        return eduPlacementMapper.importInsertEduPlacement(eduPlacementList);
    }
}

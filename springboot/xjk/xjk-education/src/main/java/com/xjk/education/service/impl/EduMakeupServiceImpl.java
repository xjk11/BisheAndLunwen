package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduStuMakeupVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduMakeupMapper;
import com.xjk.education.domain.EduMakeup;
import com.xjk.education.service.IEduMakeupService;

/**
 * 补考成绩Service业务层处理
 *
 * @author xjk
 * @date 2025-02-03
 */
@Service
public class EduMakeupServiceImpl implements IEduMakeupService
{
    @Autowired
    private EduMakeupMapper eduMakeupMapper;

    /**
     * 查询补考成绩
     *
     * @param makeupId 补考成绩主键
     * @return 补考成绩
     */
    @Override
    public EduMakeup selectEduMakeupByMakeupId(Long makeupId)
    {
        return eduMakeupMapper.selectEduMakeupByMakeupId(makeupId);
    }

    /**
     * 查询补考成绩列表
     *
     * @param eduMakeup 补考成绩
     * @return 补考成绩
     */
    @Override
    public List<EduMakeup> selectEduMakeupList(EduMakeup eduMakeup)
    {
        return eduMakeupMapper.selectEduMakeupList(eduMakeup);
    }

    @Override
    public List<EduStuMakeupVo> selectStuEduMakeupList(EduMakeup eduMakeup)
    {
        return eduMakeupMapper.selectStuEduMakeupList(eduMakeup);
    }

    @Override
    public List<EduStuMakeupVo> selectTeaEduMakeupList(EduMakeup eduMakeup)
    {
        return eduMakeupMapper.selectTeaEduMakeupList(eduMakeup);
    }

    /**
     * 新增补考成绩
     *
     * @param eduMakeup 补考成绩
     * @return 结果
     */
    @Override
    public int insertEduMakeup(EduMakeup eduMakeup)
    {
        return eduMakeupMapper.insertEduMakeup(eduMakeup);
    }

    /**
     * 修改补考成绩
     *
     * @param eduMakeup 补考成绩
     * @return 结果
     */
    @Override
    public int updateEduMakeup(EduMakeup eduMakeup)
    {
        return eduMakeupMapper.updateEduMakeup(eduMakeup);
    }

    /**
     * 批量删除补考成绩
     *
     * @param makeupIds 需要删除的补考成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduMakeupByMakeupIds(Long[] makeupIds)
    {
        return eduMakeupMapper.deleteEduMakeupByMakeupIds(makeupIds);
    }

    /**
     * 删除补考成绩信息
     *
     * @param makeupId 补考成绩主键
     * @return 结果
     */
    @Override
    public int deleteEduMakeupByMakeupId(Long makeupId)
    {
        return eduMakeupMapper.deleteEduMakeupByMakeupId(makeupId);
    }

    @Override
    public int importInsertEduMakeup(List<EduMakeup> eduMakeupList)
    {
        return eduMakeupMapper.importInsertEduMakeup(eduMakeupList);
    }
}

package com.xjk.education.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduTeaStuMapper;
import com.xjk.education.domain.EduTeaStu;
import com.xjk.education.service.IEduTeaStuService;

/**
 * 师生关系管理Service业务层处理
 *
 * @author xjk
 * @date 2025-03-19
 */
@Service
public class EduTeaStuServiceImpl implements IEduTeaStuService
{
    @Autowired
    private EduTeaStuMapper eduTeaStuMapper;

    /**
     * 查询师生关系管理
     *
     * @param teaStuId 师生关系管理主键
     * @return 师生关系管理
     */
    @Override
    public EduTeaStu selectEduTeaStuByTeaStuId(Long teaStuId)
    {
        return eduTeaStuMapper.selectEduTeaStuByTeaStuId(teaStuId);
    }

    /**
     * 查询师生关系管理列表
     *
     * @param eduTeaStu 师生关系管理
     * @return 师生关系管理
     */
    @Override
    public List<EduTeaStu> selectEduTeaStuList(EduTeaStu eduTeaStu)
    {
        return eduTeaStuMapper.selectEduTeaStuList(eduTeaStu);
    }

    @Override
    public List<EduTeaStu> selectEduTeaStuWithNames(EduTeaStu eduTeaStu)
    {
        return eduTeaStuMapper.selectEduTeaStuWithNames(eduTeaStu);
    }

    /**
     * 新增师生关系管理
     *
     * @param eduTeaStu 师生关系管理
     * @return 结果
     */
    @Override
    public int insertEduTeaStu(EduTeaStu eduTeaStu)
    {
        return eduTeaStuMapper.insertEduTeaStu(eduTeaStu);
    }

    /**
     * 修改师生关系管理
     *
     * @param eduTeaStu 师生关系管理
     * @return 结果
     */
    @Override
    public int updateEduTeaStu(EduTeaStu eduTeaStu)
    {
        return eduTeaStuMapper.updateEduTeaStu(eduTeaStu);
    }

    /**
     * 批量删除师生关系管理
     *
     * @param teaStuIds 需要删除的师生关系管理主键
     * @return 结果
     */
    @Override
    public int deleteEduTeaStuByTeaStuIds(Long[] teaStuIds)
    {
        return eduTeaStuMapper.deleteEduTeaStuByTeaStuIds(teaStuIds);
    }

    /**
     * 删除师生关系管理信息
     *
     * @param teaStuId 师生关系管理主键
     * @return 结果
     */
    @Override
    public int deleteEduTeaStuByTeaStuId(Long teaStuId)
    {
        return eduTeaStuMapper.deleteEduTeaStuByTeaStuId(teaStuId);
    }
}

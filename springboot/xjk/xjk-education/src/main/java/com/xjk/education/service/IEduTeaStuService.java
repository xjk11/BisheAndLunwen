package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduTeaStu;

/**
 * 师生关系管理Service接口
 *
 * @author xjk
 * @date 2025-03-19
 */
public interface IEduTeaStuService
{
    /**
     * 查询师生关系管理
     *
     * @param teaStuId 师生关系管理主键
     * @return 师生关系管理
     */
    public EduTeaStu selectEduTeaStuByTeaStuId(Long teaStuId);

    /**
     * 查询师生关系管理列表
     *
     * @param eduTeaStu 师生关系管理
     * @return 师生关系管理集合
     */
    public List<EduTeaStu> selectEduTeaStuList(EduTeaStu eduTeaStu);

    public List<EduTeaStu> selectEduTeaStuWithNames (EduTeaStu eduTeaStu);

    /**
     * 新增师生关系管理
     *
     * @param eduTeaStu 师生关系管理
     * @return 结果
     */
    public int insertEduTeaStu(EduTeaStu eduTeaStu);

    /**
     * 修改师生关系管理
     *
     * @param eduTeaStu 师生关系管理
     * @return 结果
     */
    public int updateEduTeaStu(EduTeaStu eduTeaStu);

    /**
     * 批量删除师生关系管理
     *
     * @param teaStuIds 需要删除的师生关系管理主键集合
     * @return 结果
     */
    public int deleteEduTeaStuByTeaStuIds(Long[] teaStuIds);

    /**
     * 删除师生关系管理信息
     *
     * @param teaStuId 师生关系管理主键
     * @return 结果
     */
    public int deleteEduTeaStuByTeaStuId(Long teaStuId);
}

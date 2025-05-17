package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduMakeup;
import com.xjk.education.domain.vo.EduStuMakeupVo;

/**
 * 补考成绩Mapper接口
 *
 * @author xjk
 * @date 2025-02-03
 */
public interface EduMakeupMapper
{
    /**
     * 查询补考成绩
     *
     * @param makeupId 补考成绩主键
     * @return 补考成绩
     */
    public EduMakeup selectEduMakeupByMakeupId(Long makeupId);

    /**
     * 查询补考成绩列表
     *
     * @param eduMakeup 补考成绩
     * @return 补考成绩集合
     */
    public List<EduMakeup> selectEduMakeupList(EduMakeup eduMakeup);


    public List<EduStuMakeupVo> selectStuEduMakeupList(EduMakeup eduMakeup);

    public List<EduStuMakeupVo> selectTeaEduMakeupList(EduMakeup eduMakeup);

    /**
     * 新增补考成绩
     *
     * @param eduMakeup 补考成绩
     * @return 结果
     */
    public int insertEduMakeup(EduMakeup eduMakeup);

    public int importInsertEduMakeup(List<EduMakeup> eduMakeupList);

    /**
     * 修改补考成绩
     *
     * @param eduMakeup 补考成绩
     * @return 结果
     */
    public int updateEduMakeup(EduMakeup eduMakeup);

    /**
     * 删除补考成绩
     *
     * @param makeupId 补考成绩主键
     * @return 结果
     */
    public int deleteEduMakeupByMakeupId(Long makeupId);

    /**
     * 批量删除补考成绩
     *
     * @param makeupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduMakeupByMakeupIds(Long[] makeupIds);
}

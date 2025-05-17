package com.xjk.education.mapper;

import java.util.List;
import com.xjk.education.domain.EduAvg;
import com.xjk.education.domain.vo.EduAvgVo;

/**
 * 班级平均成绩Mapper接口
 *
 * @author xjk
 * @date 2025-02-03
 */
public interface EduAvgMapper
{
    /**
     * 查询班级平均成绩
     *
     * @param avgId 班级平均成绩主键
     * @return 班级平均成绩
     */
    public EduAvg selectEduAvgByAvgId(Long avgId);

    /**
     * 查询班级平均成绩列表
     *
     * @param eduAvg 班级平均成绩
     * @return 班级平均成绩集合
     */
    public List<EduAvg> selectEduAvgList(EduAvg eduAvg);


    // 新增的方法
    public List<EduAvgVo> selectAverageScoreByClassCourseTeacher(EduAvg eduAvg);

    /**
     * 新增班级平均成绩
     *
     * @param eduAvg 班级平均成绩
     * @return 结果
     */
    public int insertEduAvg(EduAvg eduAvg);

    /**
     * 修改班级平均成绩
     *
     * @param eduAvg 班级平均成绩
     * @return 结果
     */
    public int updateEduAvg(EduAvg eduAvg);

    /**
     * 删除班级平均成绩
     *
     * @param avgId 班级平均成绩主键
     * @return 结果
     */
    public int deleteEduAvgByAvgId(Long avgId);

    /**
     * 批量删除班级平均成绩
     *
     * @param avgIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEduAvgByAvgIds(Long[] avgIds);
}

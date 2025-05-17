package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduExamUser;
import com.xjk.education.domain.vo.EduExamUserVo;

/**
 * 个人考试信息查询Service接口
 *
 * @author xjk
 * @date 2025-02-07
 */
public interface IEduExamUserService
{
    /**
     * 查询个人考试信息查询
     *
     * @param userExamId 考试信息用户信息关联主键
     * @return 考试信息用户信息关联
     */
    public EduExamUser selectEduExamUserByUserExamId(Long userExamId);

    /**
     * 查询个人考试信息查询列表
     *
     * @param eduExamUser 个人考试信息查询
     * @return 个人考试信息查询集合
     */
    public List<EduExamUser> selectEduExamUserList(EduExamUser eduExamUser);

    public List<EduExamUserVo> selectEduExamUserVoList(EduExamUserVo eduExamUserVo);

    /**
     * 新增个人考试信息查询
     *
     * @param eduExamUser 个人考试信息查询
     * @return 结果
     */
    public int insertEduExamUser(EduExamUser eduExamUser);

    /**
     * 修改个人考试信息查询
     *
     * @param eduExamUser 个人考试信息查询
     * @return 结果
     */
    public int updateEduExamUser(EduExamUser eduExamUser);

    /**
     * 批量删除考试信息用户信息关联
     *
     * @param userExamIds 需要删除的考试信息用户信息关联主键集合
     * @return 结果
     */
    public int deleteEduExamUserByUserExamIds(Long[] userExamIds);

    /**
     * 删除考试信息用户信息关联信息
     *
     * @param userExamId 考试信息用户信息关联主键
     * @return 结果
     */
    public int deleteEduExamUserByUserExamId(Long userExamId);
}

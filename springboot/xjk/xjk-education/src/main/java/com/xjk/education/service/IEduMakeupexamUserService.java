package com.xjk.education.service;

import java.util.List;
import com.xjk.education.domain.EduMakeupexamUser;
import com.xjk.education.domain.vo.EduExamUserVo;

/**
 * 个人补考信息查询Service接口
 *
 * @author xjk
 * @date 2025-02-07
 */
public interface IEduMakeupexamUserService
{
    /**
     * 查询个人补考信息查询
     *
     * @param userMakeupexamId 补考信息用户信息关联主键
     * @return 补考信息用户信息关联
     */
    public EduMakeupexamUser selectEduMakeupexamUserByUserMakeupexamId(Long userMakeupexamId);

    /**
     * 查询个人补考信息查询列表
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 个人补考信息查询集合
     */
    public List<EduMakeupexamUser> selectEduMakeupexamUserList(EduMakeupexamUser eduMakeupexamUser);

    public List<EduExamUserVo> selectEduMakeupExamUserVoList(EduExamUserVo eduExamUserVo);

    /**
     * 新增个人补考信息查询
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 结果
     */
    public int insertEduMakeupexamUser(EduMakeupexamUser eduMakeupexamUser);

    /**
     * 修改个人补考信息查询
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 结果
     */
    public int updateEduMakeupexamUser(EduMakeupexamUser eduMakeupexamUser);

    /**
     * 批量删除补考信息用户信息关联
     *
     * @param userMakeupexamIds 需要删除的补考信息用户信息关联主键集合
     * @return 结果
     */
    public int deleteEduMakeupexamUserByUserMakeupexamIds(Long[] userMakeupexamIds);

    /**
     * 删除补考信息用户信息关联信息
     *
     * @param userMakeupexamId 补考信息用户信息关联主键
     * @return 结果
     */
    public int deleteEduMakeupexamUserByUserMakeupexamId(Long userMakeupexamId);
}

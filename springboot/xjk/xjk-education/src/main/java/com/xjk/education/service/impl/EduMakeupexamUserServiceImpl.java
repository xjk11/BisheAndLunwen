package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduExamUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduMakeupexamUserMapper;
import com.xjk.education.domain.EduMakeupexamUser;
import com.xjk.education.service.IEduMakeupexamUserService;

/**
 * 个人补考信息查询Service业务层处理
 *
 * @author xjk
 * @date 2025-02-07
 */
@Service
public class EduMakeupexamUserServiceImpl implements IEduMakeupexamUserService
{
    @Autowired
    private EduMakeupexamUserMapper eduMakeupexamUserMapper;

    /**
     * 查询个人补考信息查询
     *
     * @param userMakeupexamId 补考信息用户信息关联主键
     * @return 补考信息用户信息关联
     */
    @Override
    public EduMakeupexamUser selectEduMakeupexamUserByUserMakeupexamId(Long userMakeupexamId)
    {
        return eduMakeupexamUserMapper.selectEduMakeupexamUserByUserMakeupexamId(userMakeupexamId);
    }

    /**
     * 查询个人补考信息查询列表
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 个人补考信息查询
     */
    @Override
    public List<EduMakeupexamUser> selectEduMakeupexamUserList(EduMakeupexamUser eduMakeupexamUser)
    {
        return eduMakeupexamUserMapper.selectEduMakeupexamUserList(eduMakeupexamUser);
    }

    @Override
    public List<EduExamUserVo> selectEduMakeupExamUserVoList(EduExamUserVo eduExamUserVo)
    {
        return eduMakeupexamUserMapper.selectEduMakeupExamUserVoList(eduExamUserVo);
    }

    /**
     * 新增个人补考信息查询
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 结果
     */
    @Override
    public int insertEduMakeupexamUser(EduMakeupexamUser eduMakeupexamUser)
    {
        return eduMakeupexamUserMapper.insertEduMakeupexamUser(eduMakeupexamUser);
    }

    /**
     * 修改个人补考信息查询
     *
     * @param eduMakeupexamUser 个人补考信息查询
     * @return 结果
     */
    @Override
    public int updateEduMakeupexamUser(EduMakeupexamUser eduMakeupexamUser)
    {
        return eduMakeupexamUserMapper.updateEduMakeupexamUser(eduMakeupexamUser);
    }

    /**
     * 批量删除补考信息用户信息关联
     *
     * @param userMakeupexamIds 需要删除的补考信息用户信息关联主键
     * @return 结果
     */
    @Override
    public int deleteEduMakeupexamUserByUserMakeupexamIds(Long[] userMakeupexamIds)
    {
        return eduMakeupexamUserMapper.deleteEduMakeupexamUserByUserMakeupexamIds(userMakeupexamIds);
    }

    /**
     * 删除补考信息用户信息关联信息
     *
     * @param userMakeupexamId 补考信息用户信息关联主键
     * @return 结果
     */
    @Override
    public int deleteEduMakeupexamUserByUserMakeupexamId(Long userMakeupexamId)
    {
        return eduMakeupexamUserMapper.deleteEduMakeupexamUserByUserMakeupexamId(userMakeupexamId);
    }
}

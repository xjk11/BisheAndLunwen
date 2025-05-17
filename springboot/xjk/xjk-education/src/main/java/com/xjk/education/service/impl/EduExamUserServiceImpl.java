package com.xjk.education.service.impl;

import java.util.List;

import com.xjk.education.domain.vo.EduExamUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xjk.education.mapper.EduExamUserMapper;
import com.xjk.education.domain.EduExamUser;
import com.xjk.education.service.IEduExamUserService;

/**
 * 个人考试信息查询Service业务层处理
 *
 * @author xjk
 * @date 2025-02-07
 */
@Service
public class EduExamUserServiceImpl implements IEduExamUserService
{
    @Autowired
    private EduExamUserMapper eduExamUserMapper;

    /**
     * 查询个人考试信息查询
     *
     * @param userExamId 考试信息用户信息关联主键
     * @return 考试信息用户信息关联
     */
    @Override
    public EduExamUser selectEduExamUserByUserExamId(Long userExamId)
    {
        return eduExamUserMapper.selectEduExamUserByUserExamId(userExamId);
    }

    /**
     * 查询个人考试信息查询列表
     *
     * @param eduExamUser 个人考试信息查询
     * @return 个人考试信息查询
     */
    @Override
    public List<EduExamUser> selectEduExamUserList(EduExamUser eduExamUser)
    {
        return eduExamUserMapper.selectEduExamUserList(eduExamUser);
    }

    @Override
    public List<EduExamUserVo> selectEduExamUserVoList(EduExamUserVo eduExamUserVo)
    {
        return eduExamUserMapper.selectEduExamUserVoList(eduExamUserVo);
    }

    /**
     * 新增个人考试信息查询
     *
     * @param eduExamUser 个人考试信息查询
     * @return 结果
     */
    @Override
    public int insertEduExamUser(EduExamUser eduExamUser)
    {
        return eduExamUserMapper.insertEduExamUser(eduExamUser);
    }

    /**
     * 修改个人考试信息查询
     *
     * @param eduExamUser 个人考试信息查询
     * @return 结果
     */
    @Override
    public int updateEduExamUser(EduExamUser eduExamUser)
    {
        return eduExamUserMapper.updateEduExamUser(eduExamUser);
    }

    /**
     * 批量删除考试信息用户信息关联
     *
     * @param userExamIds 需要删除的考试信息用户信息关联主键
     * @return 结果
     */
    @Override
    public int deleteEduExamUserByUserExamIds(Long[] userExamIds)
    {
        return eduExamUserMapper.deleteEduExamUserByUserExamIds(userExamIds);
    }

    /**
     * 删除考试信息用户信息关联信息
     *
     * @param userExamId 考试信息用户信息关联主键
     * @return 结果
     */
    @Override
    public int deleteEduExamUserByUserExamId(Long userExamId)
    {
        return eduExamUserMapper.deleteEduExamUserByUserExamId(userExamId);
    }
}

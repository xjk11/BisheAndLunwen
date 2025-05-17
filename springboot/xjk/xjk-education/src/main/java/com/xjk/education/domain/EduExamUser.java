package com.xjk.education.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 考试信息用户信息关联对象 edu_exam_user
 *
 * @author xjk
 * @date 2025-03-20
 */
public class EduExamUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个人考试关联id */
//    @Excel(name = "个人考试关联id")
    private Long userExamId;

    /** 考试记录ID */
    @Excel(name = "考试记录ID")
    private Long examId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    public void setUserExamId(Long userExamId)
    {
        this.userExamId = userExamId;
    }

    public Long getUserExamId()
    {
        return userExamId;
    }
    public void setExamId(Long examId)
    {
        this.examId = examId;
    }

    public Long getExamId()
    {
        return examId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("userExamId", getUserExamId())
                .append("examId", getExamId())
                .append("userId", getUserId())
                .toString();
    }
}

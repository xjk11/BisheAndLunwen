package com.xjk.education.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xjk.common.annotation.Excel;
import com.xjk.common.core.domain.BaseEntity;

/**
 * 个人选课信息查询对象 edu_userselectcourses
 *
 * @author xjk
 * @date 2025-03-04
 */
public class EduUserselectcourses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 个人选课信息id */
//    @Excel(name = "个人选课信息id")
    private Long userSelectId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 选课信息id */
    @Excel(name = "选课信息id")
    private Long selectId;

    public void setUserSelectId(Long userSelectId)
    {
        this.userSelectId = userSelectId;
    }

    public Long getUserSelectId()
    {
        return userSelectId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setSelectId(Long selectId)
    {
        this.selectId = selectId;
    }

    public Long getSelectId()
    {
        return selectId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userSelectId", getUserSelectId())
            .append("userId", getUserId())
            .append("selectId", getSelectId())
            .toString();
    }
}

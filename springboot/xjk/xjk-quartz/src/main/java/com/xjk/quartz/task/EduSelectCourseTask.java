package com.xjk.quartz.task;

import com.xjk.quartz.mapper.EduSelectCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("eduSelectCourseTask")
public class EduSelectCourseTask {

    @Autowired
    private EduSelectCourseMapper eduSelectCourseMapper;
    public void openSelectCourse()
    {
        eduSelectCourseMapper.openSelectCourse();
        System.out.println("选课功能已开启，权限已插入到 sys_role_menu 表中。");
    }

    public void closeSelectCourse()
    {
        eduSelectCourseMapper.closeSelectCourse();
        System.out.println("选课功能已停止，权限已插入到 sys_role_menu 表中。");
    }
}

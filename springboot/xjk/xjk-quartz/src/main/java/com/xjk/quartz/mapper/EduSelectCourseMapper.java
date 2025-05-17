package com.xjk.quartz.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EduSelectCourseMapper {
    void openSelectCourse();

    void closeSelectCourse();
}

package com.xjk.quartz.task;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("myTask")
public class MyTask {

    public void showTime()
    {
        System.out.println("当前时间：" + new Date());
    }

}

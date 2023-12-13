package com.li.lrms.labusetime.scheduled;


import com.li.lrms.labusetime.service.LabuseService;
import com.li.lrms.model.labuse.Labuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ScheduledTask {
    @Autowired
    private LabuseService labuseService;

    // @Scheduled(cron = "0 0 1 * * ?")     //每天凌晨1点更新一次
    @Scheduled(cron = "0/5 * * * * ? ")     //每5秒更新一次

    public void task2() throws ParseException {
        Date today = new Date();

        String pattern = "yyyy-MM-dd";//定义格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(today);   //今天的日期，string格式



        labuseService.updateLab(format);
        System.out.println(format);
    }

}

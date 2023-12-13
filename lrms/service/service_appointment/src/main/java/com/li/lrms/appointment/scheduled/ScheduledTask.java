package com.li.lrms.appointment.scheduled;

import com.li.lrms.appointment.service.AppointmentStaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ScheduledTask {
    @Autowired
    private AppointmentStaService appointmentStaService;

    @Scheduled(cron = "0 0 1 * * ? ")   //每天凌晨1点添加前一天的时间的信息
    public void task1(){
        Date today=new Date();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date yesterday = calendar.getTime();  //昨天的日期

        String pattern  = "yyyy-MM-dd";//定义格式
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(yesterday);

       appointmentStaService.countAppointmentDay(format);
    }
    @Scheduled(cron = "0 0/1 * * * ? ")   //每分钟更新一次
    public void task2(){
        Date today=new Date();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date yesterday = calendar.getTime();  //昨天的日期

        String pattern  = "yyyy-MM-dd";//定义格式
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(yesterday);

        appointmentStaService.countAppointmentDay(format);
        System.out.println(format);
    }


}

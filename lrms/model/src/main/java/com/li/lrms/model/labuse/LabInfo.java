package com.li.lrms.model.labuse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public  class LabInfo {

    public  static Labuse setMonday(String labId, Date today){               //定义周一的属性   需要实验室id，当前日期+7  课表
       Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();                //新增时间为6天后
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();


        labuse.setLabId(labId);
        labuse.setWeek("Monday");
        labuse.setNowdate(today);

        LabTimeInfo.setLabMonday(labId);  //根据输入的实验室id，查询对应表


        switch (labId){
            case "1":LabTimeInfo.setLab1Monday(labuse);break;

        }

        return labuse;
   }
    public  static Labuse setTuesday(String labId, Date today){               //定义周二的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Tuesday");
        labuse.setNowdate(today);
        switch (labId){
            case "1":LabTimeInfo.setLab1Thesday(labuse);break;
        }

        return labuse;
    }
    public  static Labuse setWednesday(String labId, Date today){               //定义周三的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Wednesday");
        labuse.setNowdate(today);
        switch (labId){
            case "1":LabTimeInfo.setLab1Wednesday(labuse);break;
        }

        return labuse;
    }
    public  static Labuse setThursday(String labId, Date today){               //定义周四的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Thursday");
        labuse.setNowdate(today);

        switch (labId){
            case "1":LabTimeInfo.setLab1Thursday(labuse);break;
        }



        return labuse;
    }
    public  static Labuse setFriday(String labId, Date today){               //定义周五的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Friday");
        labuse.setNowdate(today);
        switch (labId){
            case "1":LabTimeInfo.setLab1Friday(labuse);break;
        }

        return labuse;
    }
    public  static Labuse setSaturday(String labId, Date today){               //定义周六的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Saturday");
        labuse.setNowdate(today);
        switch (labId){
            case "1":LabTimeInfo.setLab1Saturday(labuse);break;
        }

        return labuse;
    }
    public  static Labuse setSunday(String labId, Date today){               //定义周日的属性   需要实验室id，当前日期+7  课表
        Labuse labuse=new Labuse();

        Calendar calendar=new GregorianCalendar();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH,6);
        today=calendar.getTime();

        labuse.setLabId(labId);
        labuse.setWeek("Sunday");
        labuse.setNowdate(today);
        switch (labId){
            case "1":LabTimeInfo.setLab1Sunday(labuse);break;
        }

        return labuse;
    }




}
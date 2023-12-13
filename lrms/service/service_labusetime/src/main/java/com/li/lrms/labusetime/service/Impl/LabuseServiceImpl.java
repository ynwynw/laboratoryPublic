package com.li.lrms.labusetime.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.li.lrms.labusetime.mapper.LabuseMapper;
import com.li.lrms.labusetime.service.LabuseService;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.LabInfo;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.model.labuse.Labuse;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
@Service
public class LabuseServiceImpl extends ServiceImpl<LabuseMapper, Labuse> implements LabuseService {

    @Override
    public void updateLab(String today) throws ParseException {
        //1.查找所有实验室实验室的所有时间安排。（一周）
        //2.如果存在一条时间晚于当前时间，则新增一条，内容为  周二--》周一  周三--》周二
        //3.删除时间晚的一条


        //1.查找所有的实验室id，存在数组里
       String labIdList[]= baseMapper.selectLabIdList();
       String labId;
        for (int j = 0; j <labIdList.length ; j++) {

            labId=labIdList[j];

            List<Labuse> labuseList=baseMapper.selectLabuseList(labId,today);//查看实验室是否有晚于今天的时间信息

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date = formatter.parse(today);  //today的Date格式

            if (labuseList.size()>0){
                // 得到该list中所有的属性，并遍历
                //如果属性为星期一，更新星期一的课表，一下循环

                Labuse labuse;
                Labuse labuseNew = new Labuse();
                for (int i=0;i<labuseList.size();i++){
                    labuse = labuseList.get(i);
                    String labId1 = labuse.getLabId();
                    Long uid = labuse.getUid();

/*                    switch (labuse.getWeek()){
                        case "Monday": baseMapper.insert(LabInfo.setMonday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Tuesday": baseMapper.insert(LabInfo.setTuesday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Wednesday":baseMapper.insert(LabInfo.setWednesday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Thursday":baseMapper.insert(LabInfo.setThursday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Friday":baseMapper.insert(LabInfo.setFriday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Saturday":baseMapper.insert(LabInfo.setSaturday(labId1,date));baseMapper.deleteById(uid);break;
                        case "Sunday":baseMapper.insert(LabInfo.setSunday(labId1,date));baseMapper.deleteById(uid);break;
                    }*/

                    Calendar calendar=new GregorianCalendar();                //新增时间为6天后
                    calendar.setTime(date);
                    calendar.add(Calendar.DAY_OF_MONTH,6);
                    Date weekLater=calendar.getTime();
                   String week= labuse.getWeek();
                    switch (week){
                        case "Monday": {                                //周一
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加
                                labuseNew.setFirstTime(labScheduled.getFirst());
                                labuseNew.setSecondTime(labScheduled.getSecond());
                                labuseNew.setThirdTime(labScheduled.getThird());
                                labuseNew.setFourthTime(labScheduled.getFourth());
                                labuseNew.setWeek(week);
                                labuseNew.setLabId(labId1);
                                labuseNew.setNowdate(weekLater);
                                baseMapper.insert(labuseUpdate);
                                baseMapper.deleteById(uid);
                                break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Tuesday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加
                                /*labuseNew.setFirstTime(labScheduled.getFirst());
                                labuseNew.setSecondTime(labScheduled.getSecond());
                                labuseNew.setThirdTime(labScheduled.getThird());
                                labuseNew.setFourthTime(labScheduled.getFourth());
                                labuseNew.setWeek(week);
                                labuseNew.setLabId(labId1);
                                labuseNew.setNowdate(weekLater);*/
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Wednesday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加

                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Thursday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加

                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Friday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加

                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Saturday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加

                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                        case "Sunday":{
                            switch (labId1){                            //设置不同实验室
                                case "1":{
                                    LabScheduled labScheduled= baseMapper.selectLab1Sched(week);  //1.根据实验室id找到该实验室的周一信息
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater); //设置实体的属性，用于添加

                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "2":{
                                    LabScheduled labScheduled= baseMapper.selectLab2Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                                case "3":{
                                    LabScheduled labScheduled= baseMapper.selectLab3Sched(week);
                                    Labuse labuseUpdate = setLab(labuseNew, labScheduled, week, labId1, weekLater);
                                    baseMapper.insert(labuseUpdate);
                                    baseMapper.deleteById(uid);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }



    }


    @Override
    public Laboratory getLabById(Long uid) {
        Laboratory lab = baseMapper.getLab(uid);
        return lab;
    }




    public static Labuse setLab(Labuse labuseNew, LabScheduled labScheduled, String week, String labId1, Date weekLater){

        labuseNew.setFirstTime(labScheduled.getFirst());
        labuseNew.setSecondTime(labScheduled.getSecond());
        labuseNew.setThirdTime(labScheduled.getThird());
        labuseNew.setFourthTime(labScheduled.getFourth());
        labuseNew.setWeek(week);
        labuseNew.setLabId(labId1);
        labuseNew.setNowdate(weekLater);
        return labuseNew;
    }
}


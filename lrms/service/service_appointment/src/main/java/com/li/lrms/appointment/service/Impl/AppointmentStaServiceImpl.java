package com.li.lrms.appointment.service.Impl;



import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.appointment.mapper.AppointmentMapper;
import com.li.lrms.appointment.mapper.AppointmentStaMapper;
import com.li.lrms.appointment.service.AppointmentService;
import com.li.lrms.appointment.service.AppointmentStaService;
import com.li.lrms.common.utils.StringToArr;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.labuse.Labuse;
import com.li.lrms.model.statistics.Statistics;
import com.li.lrms.vo.appointment.AppointmentDict;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Service
public class AppointmentStaServiceImpl extends ServiceImpl<AppointmentStaMapper, Statistics> implements AppointmentStaService {



    //查询某一天的预约人数，填进表里
    @Override
    public void countAppointmentDay(String day) {

        Integer integer = baseMapper.countAppointment(day); //查询某日预约人数，各个实验室使用情况
        Integer lab1 = baseMapper.countAppointmentlab1(day);     //实验室1使用情况
        Integer lab2 = baseMapper.countAppointmentlab2(day);     //实验室2使用情况
        Integer lab3 = baseMapper.countAppointmentlab3(day);     //实验室3使用情况
        Integer lab4 = baseMapper.countAppointmentlab4(day);     //实验室4使用情况
        Integer lab5 = baseMapper.countAppointmentlab5(day);     //实验室5使用情况
        Integer lab6 = baseMapper.countAppointmentlab6(day);     //实验室6使用情况
        Integer lab7 = baseMapper.countAppointmentlab7(day);     //实验室7使用情况
        Integer lab8 = baseMapper.countAppointmentlab8(day);     //实验室8使用情况
        Integer lab9 = baseMapper.countAppointmentlab9(day);     //实验室9使用情况


        //2.查看同日期记录，删除
        QueryWrapper<Statistics> wrapper=new QueryWrapper();
        wrapper.eq("date_sta",day);
        baseMapper.delete(wrapper);

        //3.设置新属性
        Statistics statistics=new Statistics();
        statistics.setAppointmentNum(integer);
        statistics.setDateSta(day);
        statistics.setLab1(lab1);
        statistics.setLab2(lab2);
        statistics.setLab3(lab3);
        statistics.setLab4(lab4);
        statistics.setLab5(lab5);
        statistics.setLab6(lab6);
        statistics.setLab7(lab7);
        statistics.setLab8(lab8);
        statistics.setLab9(lab9);


        //4.填进统计表
        baseMapper.insert(statistics);

    }

    //查出数据返回
    @Override
    public Map<String, Object> showDataMap(String type, String begin, String end) {
        QueryWrapper<Statistics> wrapper=new QueryWrapper<>();
        wrapper.between("date_sta",begin,end);
        wrapper.select("date_sta",type);

        List<Statistics> staList = baseMapper.selectList(wrapper);

        //返回日期和数量，两个list集合
        List<String> date_staList=new ArrayList<>();
        List<Integer> numDataList=new ArrayList<>();

        //便利所有数据list集合封装
        for (int i = 0; i < staList.size(); i++) {
            Statistics daily = staList.get(i);
            //封装日期集合
            date_staList.add(daily.getDateSta());
            //封装数据集合
            switch (type){
                case "lab1":numDataList.add(daily.getLab1());break;
                case "lab2":numDataList.add(daily.getLab2());break;
                case "lab3":numDataList.add(daily.getLab3());break;
                case "lab4":numDataList.add(daily.getLab4());break;
                case "lab5":numDataList.add(daily.getLab5());break;
                case "lab6":numDataList.add(daily.getLab6());break;
                case "lab7":numDataList.add(daily.getLab7());break;
                case "lab8":numDataList.add(daily.getLab8());break;
                case "lab9":numDataList.add(daily.getLab9());break;
                case "appointment_num":numDataList.add(daily.getAppointmentNum());break;
                default:break;
            }

        }
        Map<String,Object>map=new HashMap<>();
        map.put("date_staList",date_staList);  //时间集合
        map.put("numDataList",numDataList);   //数据集

        return map;

    }
}

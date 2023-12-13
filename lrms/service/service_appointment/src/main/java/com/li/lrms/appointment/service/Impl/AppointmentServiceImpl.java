package com.li.lrms.appointment.service.Impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//
//import com.li.lrms.mapper.UsersMapper;
//import com.li.lrms.model.user.Users;
//
//import com.li.lrms.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.li.lrms.appointment.mapper.AppointmentMapper;
import com.li.lrms.appointment.service.AppointmentService;

import com.li.lrms.common.utils.StringToArr;


import com.li.lrms.model.appointment.Appointment;

import com.li.lrms.model.labuse.Labuse;

import com.li.lrms.vo.appointment.AppointmentDict;

import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentServiceImpl extends ServiceImpl<AppointmentMapper, Appointment> implements AppointmentService {
//    @Autowired
//    private EquTimeMapper equTimeMapper;
    //    //同意申请
    @Override
    public boolean agreeAppointmentFirst(Long uid) {         //一级审核

        Appointment appointment = baseMapper.selectById(uid);    //获取预约申请表中的对象
     /*   //如果该实验室该时间段已经满了，则拒绝预约
        //1.根据填的实验室id和日期时间段查询剩余容量
        //2.与输入的申请人数比较
        String labId = appointment.getLabId();
        Date appointDate = appointment.getAppointDate();
        Integer appointTime = appointment.getAppointTime();
        Integer count = appointment.getCount();
        Labuse labuse = baseMapper.check(labId, appointDate);  //获取当天预约状况*/

/*
        if (appointTime==1&&labuse.getFirstTime()<count)   //预约第一节但是第一节 余量不够
            return false;
        if (appointTime==2&&labuse.getSecondTime()<count)
            return false;
        if (appointTime==3&&labuse.getThirdTime()<count)
            return false;
        if (appointTime==4&&labuse.getFourthTime()<count)
            return false;*/
        //余量都够
        appointment.setCheckFirst("同意");          //一级意见

        if (appointment.getStatus().equals("审核中"))
        {
            baseMapper.updateById(appointment);
            return true;
        }
        else
            return false;

    }

    @Override
    public boolean agreeAppointmentSecond(Long uid) {               //二级审核
        Appointment appointment = baseMapper.selectById(uid);       //根据传入id查找该对象
        //一级管理员审核过容量问题后，后面的就不用审核了
        if (appointment.getCheckFirst().equals("同意")&&appointment.getStatus().equals("审核中"))  {   //只有在一级审核通过后
            appointment.setCheckSecond("同意");                     //将第二部审核通过
            baseMapper.updateById(appointment);
            return true;
        }
        else                                                         //否则审核失败
            return false;

    }

    @Override
    public boolean agreeAppointmentThird(Long uid) {          //三级审核

        Appointment appointment = baseMapper.selectById(uid);   //查找该对象
        if (appointment.getCheckFirst().equals("同意")&&appointment.getCheckSecond().equals("同意")&&appointment.getStatus().equals("审核中")) { //只有在一级、二级审核通过后
            //更新三级状态和整体状态
            appointment.setCheckThird("同意");
            appointment.setStatus("审核通过");                         //整个申请通过
            baseMapper.updateById(appointment);

            //更新实验室使用表
            // 1.查找  实验室id，使用日期，使用时间段
            String labId = appointment.getLabId();
            Date appointDate = appointment.getAppointDate();
            Integer appointTime = appointment.getAppointTime();
            Integer count = appointment.getCount();              //申请的人数
            Labuse check = baseMapper.check(labId, appointDate);
            int flag;


            //2.更新   实验室时间段的人数减count
            if (appointTime == 1) {
                flag=check.getFirstTime()-count;
                baseMapper.updateLabuseByIdFirst(labId, appointDate,flag);
            } else if (appointTime == 2) {
                flag=check.getSecondTime()-count;
                 baseMapper.updateLabuseByIdSecond(labId, appointDate,flag);
            } else if (appointTime == 3) {
                flag=check.getThirdTime()-count;
                baseMapper.updateLabuseByIdThird(labId, appointDate,flag);
            } else if (appointTime == 4) {
                flag=check.getFourthTime()-count;
                baseMapper.updateLabuseByIdFourth(labId, appointDate,flag);
            }

     /*       //3.更新设备表，
            //将该时间段的设备状态改为0
            //需要  设备id  日期  时间段
            String equipmentIdList = appointment.getEquipmentIdList();
            //获取设备列表  将设备列表从字符串中提取出来，存到数组中
            ArrayList<Double> doubles = StringToArr.NumberString(equipmentIdList);
            int use=1;
            for (int i=0;i<doubles.size();i++){
                Double aDouble = doubles.get(i);  //设备id
                int i1 = aDouble.intValue();
                String equId = String.valueOf(i1);
                if (appointTime==1)   baseMapper.updateEqu1(equId,appointDate,use);
                if (appointTime==2)   baseMapper.updateEqu2(equId,appointDate,use);
                if (appointTime==3)   baseMapper.updateEqu3(equId,appointDate,use);
                if (appointTime==4)   baseMapper.updateEqu4(equId,appointDate,use);
            }*/
            return true;
        }

        else
            return false;
    }

    //拒绝申请

    @Override
    public boolean refuseAppointmentFirst(Long uid) {
        Appointment appointment=new Appointment();
        appointment.setUid(uid);

            appointment.setCheckFirst("拒绝");     //将第一部设为拒绝
            appointment.setStatus("未通过");         //整个申请拒绝
            baseMapper.updateById(appointment);

        Appointment appointment1 = baseMapper.selectById(uid);
        Date appointDate = appointment1.getAppointDate();
        Integer appointTime = appointment1.getAppointTime();
        String equipmentIdList = appointment1.getEquipmentIdList();
        ArrayList<Double> doubles = StringToArr.NumberString(equipmentIdList);
        int use=0;
        for (int i=0;i<doubles.size();i++){
            Double aDouble = doubles.get(i);  //设备id
            int i1 = aDouble.intValue();
            String equId = String.valueOf(i1);
            if (appointTime==1)   baseMapper.updateEqu1(equId,appointDate,use);
            if (appointTime==2)   baseMapper.updateEqu2(equId,appointDate,use);
            if (appointTime==3)   baseMapper.updateEqu3(equId,appointDate,use);
            if (appointTime==4)   baseMapper.updateEqu4(equId,appointDate,use);
        }
            return true;

    }

    @Override
    public boolean refuseAppointmentSecond(Long uid) {
        Appointment appointment=new Appointment();
        appointment.setUid(uid);
        appointment.setCheckSecond("拒绝");
        appointment.setStatus("未通过");
        baseMapper.updateById(appointment);

        Appointment appointment1 = baseMapper.selectById(uid);
        Date appointDate = appointment1.getAppointDate();
        Integer appointTime = appointment1.getAppointTime();
        String equipmentIdList = appointment1.getEquipmentIdList();
        ArrayList<Double> doubles = StringToArr.NumberString(equipmentIdList);
        int use=0;
        for (int i=0;i<doubles.size();i++){
            Double aDouble = doubles.get(i);  //设备id
            int i1 = aDouble.intValue();
            String equId = String.valueOf(i1);
            if (appointTime==1)   baseMapper.updateEqu1(equId,appointDate,use);
            if (appointTime==2)   baseMapper.updateEqu2(equId,appointDate,use);
            if (appointTime==3)   baseMapper.updateEqu3(equId,appointDate,use);
            if (appointTime==4)   baseMapper.updateEqu4(equId,appointDate,use);
        }


        return true;

    }

    @Override
    public boolean refuseAppointmentThird(Long uid) {
        Appointment appointment=new Appointment();
        appointment.setUid(uid);
        appointment.setCheckThird("拒绝");
        appointment.setStatus("未通过");
        baseMapper.updateById(appointment);

        Appointment appointment1 = baseMapper.selectById(uid);
        Date appointDate = appointment1.getAppointDate();
        Integer appointTime = appointment1.getAppointTime();
        String equipmentIdList = appointment1.getEquipmentIdList();
        ArrayList<Double> doubles = StringToArr.NumberString(equipmentIdList);
        int use=0;
        for (int i=0;i<doubles.size();i++){
            Double aDouble = doubles.get(i);  //设备id
            int i1 = aDouble.intValue();
            String equId = String.valueOf(i1);
            if (appointTime==1)   baseMapper.updateEqu1(equId,appointDate,use);
            if (appointTime==2)   baseMapper.updateEqu2(equId,appointDate,use);
            if (appointTime==3)   baseMapper.updateEqu3(equId,appointDate,use);
            if (appointTime==4)   baseMapper.updateEqu4(equId,appointDate,use);
        }
        return true;
    }


    //导出数据
    @Override
    public void exportAppointmentData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName="dict";
        response.setHeader("Content-disposition","attachment:filename"+fileName+".xlsx");

        List<Appointment> appointmentList=baseMapper.selectList(null);  //获取网页所有对象



        List<AppointmentDict> appointmentDictList=new ArrayList<>();

        for (Appointment appointment:appointmentList){

            AppointmentDict appointmentDict=new AppointmentDict();

            BeanUtils.copyProperties(appointment,appointmentDict);

            appointmentDictList.add(appointmentDict);
        }

        //调用方法写操作
        try {
            EasyExcel.write(response.getOutputStream(), AppointmentDict.class).sheet("sheet1").doWrite(appointmentDictList);
        }catch (IOException e){
            e.printStackTrace();
        }

    }



    @Override
    public String getUserById(String memberId) {

        String role = baseMapper.selectUserById(memberId);
        return role;
    }

    @Override
    public boolean check(Appointment appointment) {   //获取实验室使用表信息


        String labId = appointment.getLabId();
        Date appointDate = appointment.getAppointDate();
        Integer appointTime = appointment.getAppointTime();
        Integer count = appointment.getCount();
        Labuse labuse = baseMapper.check(labId, appointDate);  //获取当天预约状况

        if (appointTime==1&&labuse.getFirstTime()<count)   //预约第一节但是第一节 没有余量
                return false;
        if (appointTime==2&&labuse.getSecondTime()<count)
            return false;
        if (appointTime==3&&labuse.getThirdTime()<count)
            return false;
        if (appointTime==4&&labuse.getFourthTime()<count)
            return false;

        return true;

    }

    //查询某一天的预约人数
    @Override
    public Integer countAppointmentDay(String day) {

        return baseMapper.countAppointment(day);
    }
}

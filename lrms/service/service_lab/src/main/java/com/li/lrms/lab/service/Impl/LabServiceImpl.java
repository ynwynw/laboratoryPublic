package com.li.lrms.lab.service.Impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//
//import com.li.lrms.mapper.UsersMapper;
//import com.li.lrms.model.user.Users;
//
//import com.li.lrms.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.lab.mapper.LabMapper;
import com.li.lrms.lab.service.LabService;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.lab.LabEqu;
import com.li.lrms.model.lab.Laboratory;

import com.li.lrms.model.labuse.Labuse;
import net.bytebuddy.matcher.FilterableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabServiceImpl extends ServiceImpl<LabMapper, Laboratory> implements LabService {
    @Override

    public Labuse searchFreeTime(String labId, Date appointDate) {

        Labuse labuse=baseMapper.selectOneLabuse(labId,appointDate);
        return labuse;
    }

    @Override
    public String getLabID(String uid) {
        QueryWrapper<Laboratory> wrapper=new QueryWrapper<>();

        wrapper.eq("uid",uid);

        Laboratory laboratory = baseMapper.selectOne(wrapper);

        return laboratory.getLabId();
    }

    @Override
    public List<Equipment> selectEquipById(Long uid) {
        //根据uid查询实验室id
        //1.根据id查询该实验室的设备id  labuse
        //2.根据设备的id查询设备信息
        //3.将设备信息返回

        QueryWrapper<Laboratory> wrapper=new QueryWrapper<>();
        wrapper.eq("uid",uid);
        Laboratory laboratory = baseMapper.selectOne(wrapper);
        String labId=laboratory.getLabId();
        String[] equipmentId=new String[100];

        List<LabEqu> labEqus = baseMapper.selectEquipmentId(labId);   //实验室设备实体集


        List<Equipment>equipmentList=new ArrayList<>();
        Equipment equipment=new Equipment();

       //取出实体集合中的属性
        for (int i=0;i<labEqus.size();i++) {
             LabEqu labEqu=labEqus.get(i);
             equipmentId[i]=labEqu.getEquId();

            equipment= baseMapper.selectEquipment(equipmentId[i]);
            equipmentList.add(equipment);
        }

     return equipmentList;


    }




}

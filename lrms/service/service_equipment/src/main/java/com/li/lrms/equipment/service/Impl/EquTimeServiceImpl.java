package com.li.lrms.equipment.service.Impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.equipment.mapper.EquMapper;
import com.li.lrms.equipment.mapper.EquTimeMapper;
import com.li.lrms.equipment.service.EquTimeService;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.equipment.EquipmentTime;
import com.li.lrms.vo.equipment.EquipmentTimeVo;
import com.li.lrms.vo.equipment.EquipmentUpdateVo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquTimeServiceImpl extends ServiceImpl<EquTimeMapper, EquipmentTime> implements EquTimeService {

    /**
     * 寻找符合该日期的设备信息
      * @param equipmentTimeVo
     */
    @Override
    public List<Equipment> findEqu(EquipmentTimeVo equipmentTimeVo) {
        Date date = equipmentTimeVo.getDate();

        Integer time = equipmentTimeVo.getTime();
        Long uid = equipmentTimeVo.getUid();

        List<Equipment> equipmentList=new ArrayList<>();

        //1.根据uid找到该实验室id
        String labId = baseMapper.selectLabId(uid);
        //2.根据实验室id，日期，时间段，找到符合条件的设备信息
        int flag=0;
        if (time==1){
            equipmentList = baseMapper.selectEquListFirst(labId, date, flag);
        }
        if (time==2){
            equipmentList = baseMapper.selectEquListSecond(labId,date,flag);
        }
        if (time==3){
             equipmentList =baseMapper.selectEquListThird(labId,date,flag);
        }
        if (time==4){
            equipmentList = baseMapper.selectEquListFourth(labId,date,flag);
        }
        return equipmentList;
    }

    /**
     * 更新设备的某日状态值
     * 输入
     * @param equipmentUpdateVo
     */
    @Override
    public void updateEquipmentTime(EquipmentUpdateVo equipmentUpdateVo) {
        //输入 日期，时间段，设备id，把这一时间段设为1
        Date date = equipmentUpdateVo.getDate();
        Integer time = equipmentUpdateVo.getTime();
        String equId = equipmentUpdateVo.getEquId();
        int flag=1;

        EquipmentTime equipmentTime = baseMapper.selectEquInfo(equId,date);
        if (time==1)   equipmentTime.setFirstTime(1);
        if (time==2)  equipmentTime.setSecondTime(1);
        if (time==3)   equipmentTime.setThirdTime(1);
        if (time==4)   equipmentTime.setFourthTime(1);

       baseMapper.updateById(equipmentTime);

    }
}

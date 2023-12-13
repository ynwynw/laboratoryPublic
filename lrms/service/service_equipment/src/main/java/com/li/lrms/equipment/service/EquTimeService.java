package com.li.lrms.equipment.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.equipment.EquipmentTime;
import com.li.lrms.vo.equipment.EquipmentTimeVo;
import com.li.lrms.vo.equipment.EquipmentUpdateVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquTimeService extends  IService<EquipmentTime> {

    List<Equipment> findEqu(EquipmentTimeVo equipmentTimeVo);

    void updateEquipmentTime(EquipmentUpdateVo equipmentUpdateVo);
}
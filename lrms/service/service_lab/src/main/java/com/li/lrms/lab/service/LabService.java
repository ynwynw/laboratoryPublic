package com.li.lrms.lab.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.Labuse;

import java.util.Date;
import java.util.List;

public interface LabService extends IService<Laboratory>{

    Labuse searchFreeTime(String labId, Date appointDate);

    String getLabID(String uid);

    List<Equipment> selectEquipById(Long uid);


}

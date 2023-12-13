package com.li.lrms.equipment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.equipment.EquipmentTime;
import io.swagger.annotations.SwaggerDefinition;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface  EquTimeMapper extends BaseMapper<EquipmentTime> {

    @Select("select lab_id from lab where uid=#{uid}")
    String  selectLabId(Long uid);

    //设备空余时间
    @Select("select * from equipment where equ_id in(select equ_id from lab_equ,equipment_use where lab_id=#{labId} and equ_id=equip_id and first_time=#{flag} and nowdate=#{date})")
    List<Equipment> selectEquListFirst(String labId, Date date, int flag);

    @Select("select * from equipment where equ_id in(select equ_id from lab_equ,equipment_use where lab_id=#{labId} and equ_id=equip_id and second_time=#{flag} and nowdate=#{date})")
    List<Equipment> selectEquListSecond(String labId, Date date, int flag);

    @Select("select * from equipment where equ_id in(select equ_id from lab_equ,equipment_use where lab_id=#{labId} and equ_id=equip_id and third_time=#{flag} and nowdate=#{date})")
    List<Equipment> selectEquListThird(String labId, Date date, int flag);

    @Select("select * from equipment where equ_id in(select equ_id from lab_equ,equipment_use where lab_id=#{labId} and equ_id=equip_id and fourth_time=#{flag} and nowdate=#{date})")
    List<Equipment> selectEquListFourth(String labId, Date date, int flag);

    @Select("select * from equipment_use where equip_id=#{equId} and nowdate=#{date}")
    EquipmentTime selectEquInfo(String equId, Date date); //查找要更新的设备信息那一行
}

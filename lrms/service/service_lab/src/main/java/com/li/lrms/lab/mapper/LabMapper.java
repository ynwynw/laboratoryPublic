package com.li.lrms.lab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.lab.LabEqu;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.Labuse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface LabMapper extends BaseMapper<Laboratory> {

    @Select("select * from labuse where lab_id=#{labId} and nowdate=#{appointDate}")
    Labuse selectOneLabuse(String labId, Date appointDate);

    @Select("select * from lab_equ where lab_id=#{labId}")//查询实验室的设备，输入实验室id，输出实体
    List<LabEqu> selectEquipmentId(String labId);


    @Select("select* from equipment where equ_id=#{s}")
    Equipment selectEquipment(String s);    //返回设备实体



    @Select("select * from labuse where lab_id=#{labId} and nowdate<#{today}")
    List<Labuse> selectLabuseList(String labId, Date today);  //查看实验室是否有晚于今天的时间信息


}

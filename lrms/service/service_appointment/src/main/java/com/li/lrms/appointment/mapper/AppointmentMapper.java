package com.li.lrms.appointment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.equipment.EquipmentTime;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.Labuse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {


    //更新实验室使用表
    @Select("update labuse set first_time=#{flag} where lab_id=#{labId} and nowdate=#{appointDate}")
    void updateLabuseByIdFirst(@Param("labId") String labId,  Date appointDate,@Param("flag") int flag);

    @Select("update labuse set second_time=#{flag} where lab_id=#{labId} and nowdate=#{appointDate}")
    void updateLabuseByIdSecond(@Param("labId") String labId, @Param("appointDate") Date appointDate,@Param("flag") int flag);

    @Select("update labuse set third_time=#{flag} where lab_id=#{labId} and nowdate=#{appointDate}")
    void updateLabuseByIdThird(@Param("labId") String labId, @Param("appointDate") Date appointDate,@Param("flag") int flag);

    @Select("update labuse set fourth_time=#{flag} where lab_id=#{labId} and nowdate=#{appointDate}")
    void updateLabuseByIdFourth(@Param("labId") String labId, @Param("appointDate") Date appointDate,@Param("flag") int flag);

    @Select("select role from users where user_id=#{memberId}")   //根据id找到用户的role值
    String selectUserById(@Param("memberId")String memberId);

    @Select("select * from labuse where lab_id=#{labId} and nowdate=#{appointDate}")   //查询该实验室该日的使用信息情况
    Labuse check(String labId, Date appointDate);

    @Select("select * from equipment_use where equip_id=#{equId} and nowdate=#{date}")
    EquipmentTime selectEquInfo(String equId, Date appointDate);//查找要更新的设备信息那一行

    @Update("update equipment_use set first_time=#{use} where nowdate=#{appointDate} and equip_id=#{equId}")
    void updateEqu1(String equId, Date appointDate, int use); //更新设备状态1

    @Update("update equipment_use set second_time=#{use} where nowdate=#{appointDate} and equip_id=#{equId}")
    void updateEqu2(String equId, Date appointDate, int use); //更新设备状态2

    @Update("update equipment_use set third_time=#{use} where nowdate=#{appointDate} and equip_id=#{equId}")
    void updateEqu3(String equId, Date appointDate, int use); //更新设备状态3

    @Update("update equipment_use set fourth_time=#{use} where nowdate=#{appointDate} and equip_id=#{equId}")
    void updateEqu4(String equId, Date appointDate, int use); //更新设备状态4

    //查询某日预约人数
    @Select("select count(*) from appointment where date(appoint_date)=#{day}")
    Integer countAppointment(String day);
}

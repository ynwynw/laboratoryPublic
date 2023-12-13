package com.li.lrms.appointment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.equipment.EquipmentTime;
import com.li.lrms.model.labuse.Labuse;
import com.li.lrms.model.statistics.Statistics;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

import java.util.Date;

@Mapper
public interface AppointmentStaMapper extends BaseMapper<Statistics> {



    //查询某日总预约人数
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过'")
    Integer countAppointment(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=1")
    Integer countAppointmentlab1(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=2")
    Integer countAppointmentlab2(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=3")
    Integer countAppointmentlab3(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=4")
    Integer countAppointmentlab4(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=5")
    Integer countAppointmentlab5(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=6")
    Integer countAppointmentlab6(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=7")
    Integer countAppointmentlab7(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=8")
    Integer countAppointmentlab8(String day);
    @Select("select count(*) from appointment where date(appoint_date)=#{day} and is_deleted=0 and status='审核通过' and lab_id=9")
    Integer countAppointmentlab9(String day);
}

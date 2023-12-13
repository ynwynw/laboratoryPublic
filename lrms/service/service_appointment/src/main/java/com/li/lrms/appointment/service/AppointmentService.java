package com.li.lrms.appointment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.appointment.Appointment;

import javax.servlet.http.HttpServletResponse;

public interface AppointmentService extends IService<Appointment>{


    boolean agreeAppointmentFirst(Long uid);

    boolean agreeAppointmentSecond(Long uid);

    boolean agreeAppointmentThird(Long uid);

    boolean refuseAppointmentFirst(Long uid);

    boolean refuseAppointmentSecond(Long uid);

    boolean refuseAppointmentThird(Long uid);

    void exportAppointmentData(HttpServletResponse response);

    String getUserById(String memberId);

    boolean check(Appointment appointment);

    Integer countAppointmentDay(String day);
}

package com.li.lrms.appointment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.statistics.Statistics;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface AppointmentStaService extends IService<Statistics>{

    void countAppointmentDay(String day);

    Map<String, Object> showDataMap(String type, String begin, String end);
}

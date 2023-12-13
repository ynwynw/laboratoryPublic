package com.li.lrms.appointment.controller;

import com.li.lrms.appointment.service.AppointmentService;
import com.li.lrms.appointment.service.AppointmentStaService;
import com.li.lrms.common.result.Result;
import com.li.lrms.vo.sta.StatisticsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin/appointment")
public class AppointmentStaController {
    @Autowired
    private AppointmentStaService appointmentStaService;

    //1.查询某天的预约人数，各个实验室的使用人数，并填进表里
    @PostMapping("countAppointment/{day}")
    public Result countAppointment(@PathVariable String day){
        appointmentStaService.countAppointmentDay(day);
        return Result.ok();
    }

    //2.图表显示，返回两部分数据，日期和数据
    @PostMapping("showData")
    public Result showData(@RequestBody StatisticsVo statisticsVo){
        String begin = statisticsVo.getBegin();
        String end = statisticsVo.getEnd();
        String type = statisticsVo.getType();
        Map<String,Object> map=appointmentStaService.showDataMap(type,begin,end);
        return Result.ok(map);

    }
}

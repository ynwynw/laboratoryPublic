package com.li.lrms.labusetime.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.model.labuse.Labuse;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LabSchMapper extends BaseMapper<LabScheduled> {



    @Select("select * from schedule_lab1")
    List<LabScheduled> searchLab1Sched();



    @Select("select lab_id from lab where uid=#{uid}")
    String selectLabId(String uid);

    @Select("select * from schedule_lab2")
    List<LabScheduled> searchLab2Sched();

    @Select("select * from schedule_lab3")
    List<LabScheduled> searchLab3Sched();
}

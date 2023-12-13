package com.li.lrms.labusetime.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.model.labuse.Labuse;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface LabuseMapper extends BaseMapper<Labuse> {

    @Select("select * from labuse where lab_id=#{labId} and nowdate<#{today} and is_deleted=0")
    List<Labuse> selectLabuseList(String labId, String today);  //查看实验室是否有晚于今天的时间信息

    @Select("select * from lab where uid=#{uid}")
    Laboratory getLab(Long uid);
    @Select("select lab_id from lab ")
    String[] selectLabIdList();

    @Select("select * from schedule_lab1 where week=#{monday}")
    LabScheduled selectLab1Sched(String monday);
    @Select("select * from schedule_lab2 where week=#{monday}")
    LabScheduled selectLab2Sched(String monday);
    @Select("select * from schedule_lab3 where week=#{monday}")
    LabScheduled selectLab3Sched(String monday);
    @Select("select * from schedule_lab1")
    List<LabScheduled> searchLab1Sched();
}

package com.li.lrms.labusetime.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.labuse.LabScheduled;

import java.util.List;

public interface LabSchService extends IService<LabScheduled> {



    List<LabScheduled> searchLab1Sched(String labId);

    String selectByUid(String uid);

    List<LabScheduled> searchLab2Sched(String labId);

    List<LabScheduled> searchLab3Sched(String labId);
}

package com.li.lrms.labusetime.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.labusetime.mapper.LabSchMapper;
import com.li.lrms.labusetime.service.LabSchService;
import com.li.lrms.model.labuse.LabScheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabSchServiceImpl extends ServiceImpl<LabSchMapper, LabScheduled> implements LabSchService {


    @Override
    public List<LabScheduled> searchLab1Sched(String labId) {

        return baseMapper.searchLab1Sched();
    }

    @Override
    public String selectByUid(String uid) {
        return baseMapper.selectLabId(uid);
    }

    @Override
    public List<LabScheduled> searchLab2Sched(String labId) {
        return baseMapper.searchLab2Sched();
    }

    @Override
    public List<LabScheduled> searchLab3Sched(String labId) {
        return baseMapper.searchLab3Sched();
    }
}



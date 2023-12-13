package com.li.lrms.labusetime.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.model.labuse.Labuse;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface LabuseService extends IService<Labuse> {
    void updateLab(String today) throws ParseException;

    Laboratory getLabById(Long uid);



}

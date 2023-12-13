package com.li.lrms.equipment.service.Impl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.lrms.equipment.mapper.EquMapper;
import com.li.lrms.equipment.service.EquService;
import com.li.lrms.model.equipment.Equipment;
import org.springframework.stereotype.Service;

@Service
public class EquServiceImpl extends ServiceImpl<EquMapper, Equipment> implements EquService {
}

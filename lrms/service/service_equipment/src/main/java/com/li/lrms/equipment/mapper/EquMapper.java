package com.li.lrms.equipment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.lrms.model.equipment.Equipment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EquMapper extends BaseMapper<Equipment> {

    @Select("SELECT * FROM equipment where equ_id = #{equ_id}")
    List<Equipment> selectByUid(@Param("equ_id") String equ_id);

}

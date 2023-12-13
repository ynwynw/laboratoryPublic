package com.li.lrms.model.equipment;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.li.lrms.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "设备")
@TableName("equipment")
@Data
public class Equipment extends BaseEntity {

    private static final long seriaVersionUID=1L;

    private String equId;

    private  String equName;;

    private  String description;

    private String func;

    private String price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date startTime;

    private Integer status;

    private String labName;

    private String types;

//    private  String usage; //当前使用情况




}
//uid,equ_id,equ_name,description,func,price,start_time,status,lab_id,types,usage,create_time,update_time,is_deleted
//FROM equipment
// WHERE  is_deleted=0 LIMIT ?,?

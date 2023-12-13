package com.li.lrms.model.lab;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.li.lrms.model.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@TableName("lab")
@Data
public class Laboratory extends BaseEntity {

    private static final long seriaVersionUID=1L;

    private String  labId;

    private String labName;

    private String address;

    private String capacity;

    private String func;

    private String description;


    private Integer status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
   private Date appointDate;   //日期
//
//    private Integer timeFirst;    //时间段
//
//    private Integer timeSecond;



}

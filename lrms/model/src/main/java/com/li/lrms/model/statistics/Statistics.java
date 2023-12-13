package com.li.lrms.model.statistics;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@TableName("statistics")
@Data
public class Statistics {

    private static final long seriaVersionUID=1L;
    @TableId(type = IdType.AUTO)
    private Long uid;
    private String dateSta;
    private Integer appointmentNum;
    private Integer lab1;
    private Integer lab2;
    private Integer lab3;
    private Integer lab4;
    private Integer lab5;
    private Integer lab6;
    private Integer lab7;
    private Integer lab8;
    private Integer lab9;

}

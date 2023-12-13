package com.li.lrms.model.appointment;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.li.lrms.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "实验室预约情况")
@TableName("appointment")
public class Appointment extends BaseEntity {

    private String labId;

    private String labName;

    private String userId;

    private String userName;

    private Integer count;

    private String description;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointDate;//预约日期

    private Integer appointTime;//预约时间段

    private String checkFirst;//审核1

    private String checkSecond;//审核2

    private String checkThird;

    private String equipmentIdList;    //设备id列表  字符串

    private String   equipmentNameList; //设备名称列表

    @TableField(exist=false)
    private String[] equIdList;   //存储设备id的数组，用于更新操作


}

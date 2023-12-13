package com.li.lrms.model.labuse;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.li.lrms.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "实验室使用情况")
@TableName("labuse")
@Data
public class Labuse extends BaseEntity {
    private static final long seriaVersionUID=1L;

    private String labId;

    private Integer firstTime;        //第一节容量

    private Integer secondTime;

    private Integer thirdTime;

    private Integer fourthTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date nowdate;           //每日时间
    private String week;   //今天周几


}

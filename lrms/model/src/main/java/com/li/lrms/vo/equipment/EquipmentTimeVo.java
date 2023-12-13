package com.li.lrms.vo.equipment;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EquipmentTimeVo {
    @ApiModelProperty(value = "设备名称")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date date;



    private Integer time;

    private Long uid;

}
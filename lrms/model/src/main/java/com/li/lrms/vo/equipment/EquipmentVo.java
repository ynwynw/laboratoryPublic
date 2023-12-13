package com.li.lrms.vo.equipment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquipmentVo {
    @ApiModelProperty(value = "设备名称")

    private String equName;
    @ApiModelProperty(value = "设备id")
    private String equId;

}
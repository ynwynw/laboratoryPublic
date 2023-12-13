package com.li.lrms.vo.lab;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LabQueryVo {
    @ApiModelProperty(value = "实验室名称")

    private String labName;
    private String func;
}

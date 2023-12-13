package com.li.lrms.vo.labuse;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LabuseUpdateVo {
    @ApiModelProperty(value = "实验室uid")
    private Long uid;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
//    private Date today;
    private  String today;
}

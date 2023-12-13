package com.li.lrms.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryVo {
    @ApiModelProperty(value = "用户名")

    private String userName;
    @ApiModelProperty(value = "用户id")
    private String role;


}

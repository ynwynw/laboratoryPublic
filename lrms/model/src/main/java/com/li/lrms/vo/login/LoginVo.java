package com.li.lrms.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVo {
    @ApiModelProperty(value = "用户id")

    private String username;
    @ApiModelProperty(value = "用户密码")
    private String password;

    private String role;


}

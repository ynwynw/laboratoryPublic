package com.li.lrms.model.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.li.lrms.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "用户")
@TableName("users")
@Data
public class User extends BaseEntity {
    private static final long seriaVersionUID=1L;

    private String userId;

    private String userName;

    @ApiModelProperty("")

    private String password;

    @ApiModelProperty("")

    private  String phone;

    private String email;


//    private enum status{STU,TEACH};

   private Integer status;

   private String role;  //身份权限


}

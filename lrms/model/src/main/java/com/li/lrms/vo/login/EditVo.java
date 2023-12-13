package com.li.lrms.vo.login;

import lombok.Data;

@Data
public class EditVo {
    private  String pass;
    private String checkPass;

    private String phone;
    private String email;
}

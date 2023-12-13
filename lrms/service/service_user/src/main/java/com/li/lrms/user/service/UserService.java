package com.li.lrms.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.user.User;
import com.li.lrms.vo.login.EditVo;

public interface UserService extends IService<User>{

    User getByUserId(String userId);

    void updateInfo(EditVo editVo, User user);
}

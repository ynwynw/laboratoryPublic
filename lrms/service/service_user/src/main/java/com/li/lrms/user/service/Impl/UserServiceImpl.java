package com.li.lrms.user.service.Impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//
//import com.li.lrms.mapper.UsersMapper;
//import com.li.lrms.model.user.Users;
//
//import com.li.lrms.service.UsersService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.li.lrms.common.utils.MD5;
import com.li.lrms.model.user.User;
import com.li.lrms.user.mapper.UserMapper;
import com.li.lrms.user.service.UserService;
import com.li.lrms.vo.login.EditVo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    //获取用户id，返回用户实体
    @Override
    public User getByUserId(String memberId) {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",memberId);
        User user = baseMapper.selectOne(queryWrapper);
        return user;

    }
    @Override
    public void updateInfo(EditVo editVo , User user) {
        String phone = editVo.getPhone();
        String emali = editVo.getEmail();
        String pass = editVo.getPass();
        String checkPass = editVo.getCheckPass();

        if (pass.equals(checkPass)){
            user.setPassword(MD5.encrypt(pass));
        }

        user.setPhone(phone);
        user.setEmail(emali);

        baseMapper.updateById(user);

    }
}

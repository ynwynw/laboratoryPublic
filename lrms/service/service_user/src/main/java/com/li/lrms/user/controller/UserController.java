package com.li.lrms.user.controller;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;
import com.li.lrms.common.utils.JwtUtils;
import com.li.lrms.common.utils.MD5;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.user.User;
import com.li.lrms.user.service.UserService;
import com.li.lrms.vo.login.EditVo;
import com.li.lrms.vo.user.UserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/users/user")
public class UserController {
    @Autowired
    private UserService userService;

    //1.查询所有用户
    @GetMapping("findAll")
    public Result findAllUser() {
        List<User> list = userService.list();
        return Result.ok(list);
    }

    //2.增加
    @PostMapping("addUser")
    public Result addUser(@RequestBody User users) {

//        String password = users.getPassword();
        String encrypt = MD5.encrypt("123456");
        users.setPassword(encrypt);

        boolean b = userService.save(users);
        if (b) return Result.ok();
        else return Result.fail();
    }

    //3.删除
    @DeleteMapping("{id}")
    public Result removeUser(@PathVariable String id) {
        boolean b = userService.removeById(id);
        if (b) return Result.ok();
        else return Result.fail();
    }

//4.条件查询分页

    /**
     * 1.创建vo类，封装条件值
     * 2.编写controller
     * 获取当前页面current和每页对象值limit
     * 获取条件语句 hospitalSetQueryVo
     */

    @PostMapping("findpage/{current}/{limit}")
    public Result findPageUser(@PathVariable long current, @PathVariable long limit,
                               @RequestBody(required = false) UserQueryVo usersQueryVo) {
        //创建page对象,传递当前页，每页记录数
        Page<User> page = new Page<>(current, limit);

        //构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String name = usersQueryVo.getUserName();
        String role = usersQueryVo.getRole();

        if (!StringUtils.isEmpty(role)) {     //输入非空
            queryWrapper.eq("role", role);  //获取用户id
        }
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("user_name", usersQueryVo.getUserName()); //获取用户id
        }
        //调用方法实现分页查询
        IPage<User> usersPage = userService.page(page, queryWrapper);

        //返回结果
        return Result.ok(usersPage);

    }


    //按id查询
    @GetMapping("getUsers/{id}")
    public Result getUsers(@PathVariable String id) {
        User users = userService.getById(id);
        return Result.ok(users);
    }

    //修改用户信息
    @PostMapping("updateUsers")
    public Result updateUsers(@RequestBody User users) {

        String password = users.getPassword();
        String encrypt = MD5.encrypt(password);
        users.setPassword(encrypt);

        boolean update = userService.updateById(users);
        if (update) return Result.ok();
        else return Result.fail();
    }

    //批量删除
    @DeleteMapping("batchRemove")
    public Result batchRemoveUsers(@RequestBody List<String> list) {
        userService.removeByIds(list);
        return Result.ok();
    }

    //修改状态
    @PutMapping("updateStatus/{id}/{status}")
    public Result setStatus(@PathVariable String id, @PathVariable Integer status) {
        //根据id查询
        User users = userService.getById(id);
        //修改信息
        users.setStatus(status);
        //调用函数更新
        userService.updateById(users);

        return Result.ok();
    }

    //4.修改个人信息
    //1.电话号码 2.密码  3.邮箱  4.
    @PostMapping("editInfo")
    public Result editInfo(@RequestBody(required = false) EditVo editVo, HttpServletRequest request) {

        String userId = JwtUtils.getMemberIdByJwtToken(request);
        User user = userService.getByUserId(userId);   //获得该用户信息

        userService.updateInfo(editVo, user);
        return Result.ok();


    }


}

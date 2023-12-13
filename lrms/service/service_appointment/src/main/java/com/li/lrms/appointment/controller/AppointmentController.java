package com.li.lrms.appointment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.appointment.service.AppointmentService;
import com.li.lrms.common.result.Result;
import com.li.lrms.common.utils.JwtUtils;
import com.li.lrms.model.appointment.Appointment;
import com.li.lrms.model.appointment.AppointmentProgess;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.message.Msg;
import com.li.lrms.model.user.User;
import com.li.lrms.vo.appointment.CheckAppointmentVo;
import com.li.lrms.vo.lab.LabQueryVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/admin/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    //1.增加预约信息，如果当前时间此实验室被占用，则提交失败（待完成）
    // 获得实验室id，实验室名，申请人数，实验内容，日期，时间段
    @PostMapping("addAppointment")
    public Result addAppointment(@RequestBody Appointment appointment,
                                HttpServletRequest request){
        String memberId= JwtUtils.getMemberIdByJwtToken(request);   //返回用户id
        String memberName = JwtUtils.getMemberNameByJwtToken(request);  //返回用户名

        appointment.setUserId(memberId);
        appointment.setUserName(memberName);
        //判断该时间段是否已经有预约信息
        //输入 实验室id 日期  预约时间段  判断该时间段的值是否有空余
       boolean check=appointmentService.check(appointment);
        Msg msg=new Msg();
       if (check){
           boolean b=appointmentService.save(appointment);
           if (b)
               return Result.ok();
           else
               return Result.fail();
       }
       else {
           msg.setMessage("该时间没地方了");
           return Result.fail(msg);
       }
    }


    //2.显示待审核预约信息
    @PostMapping("findpage/{current}/{limit}")
    public Result findPageLab(@PathVariable long current,
                              @PathVariable long limit,
                               HttpServletRequest request) {

        Page<Appointment> page=new Page<>(current,limit);
        QueryWrapper<Appointment> wrapper=new QueryWrapper();
        //根据token获取用户信息
        String memberId= JwtUtils.getMemberIdByJwtToken(request);   //返回用户id

        String role = appointmentService.getUserById(memberId);
        if (role.equals("实验室管理员"))       {
            Map<String , Object> map = new HashMap<>();                        //如果是一级管理员登录
            map.put("check_first" , "待审核");
            map.put("status" , "审核中");
            wrapper.allEq(map);
        }
      else if(role.equals("teacher")) {                                       //如果是2级管理员登录

          Map<String , Object> map = new HashMap<>();  //一级审核已经通过且二级还未审核
            map.put("check_first" , "同意");
            map.put("check_second" , "待审核");
            map.put("status" , "审核中");
          wrapper.allEq(map);
      }
      else if(role.equals("admin")){                                  //如果是3级管理员登录
          Map<String , Object> map = new HashMap<>();
          map.put("check_first" , "同意");
          map.put("check_second" , "同意");
          map.put("check_third","待审核");
            map.put("status" , "审核中");
          wrapper.allEq(map);
      }
       IPage<Appointment> laboratoryIPage=appointmentService.page(page,wrapper);
        //返回结果
        return  Result.ok(laboratoryIPage);
    }


    //3.显示已经审核预约信息
    @PostMapping("findpageOld/{current}/{limit}")
    public Result findPageLabOld(@PathVariable long current,
                                  @PathVariable long limit,
                                 HttpServletRequest request){

        Page<Appointment> page=new Page<>(current,limit);
        QueryWrapper<Appointment> queryWrapper=new QueryWrapper();

        String memberId= JwtUtils.getMemberIdByJwtToken(request);   //返回用户id

        String role = appointmentService.getUserById(memberId);


        if (role.equals("实验室管理员"))                                       //1级管理员

            queryWrapper.in("check_first","同意","拒绝");


        else if (role.equals("teacher")) {                              //2级管理员
            queryWrapper.and(wapper->wapper.in("check_first","同意")
                                           .in("check_second","同意","拒绝"));
        }
        else if (role.equals("admin")){
            queryWrapper.and(wapper->wapper.in("check_first","同意")
                                            .in("check_second","同意")
                                            .in("check_third","同意","拒绝"));
        }


       IPage<Appointment> laboratoryIPage=appointmentService.page(page,queryWrapper);
        //返回结果
        return  Result.ok(laboratoryIPage);
    }



    //4.审核功能
    //1.根据登录人的id获取他的权限信息
    //2.根据他的权限来使某一位审核进度置为1
    //3.当所有进度都为1时，将状态置为1，预约审核成功
    @PostMapping("agreeAppointment/{uid}")
    public Result checkAppointment(@PathVariable Long uid,
                                   HttpServletRequest request){
        String memberId= JwtUtils.getMemberIdByJwtToken(request);                   //返回用户id
        String role = appointmentService.getUserById(memberId);                     //返回用户权限
        if (role.equals("实验室管理员")){                                             //身份为管理员一级
            boolean flag=appointmentService.agreeAppointmentFirst(uid);
            if (flag)
                return Result.ok();
            else
                return Result.fail();
        }
        else if (role.equals("teacher")){                                           //身份为管理员二级
            boolean b = appointmentService.agreeAppointmentSecond(uid);
            if (b)
            return Result.ok();
            else return Result.fail();
        }
        else {                                                       //身份为管理员三级
            boolean b = appointmentService.agreeAppointmentThird(uid);
            if (b)
                return Result.ok();
            else
                return Result.fail();
        }
    }

    //4.2拒绝申请
    @PostMapping("refuseAppointment/{uid}")
    public Result refuseAppointment(@PathVariable Long uid,
                                    HttpServletRequest request){

        String memberId= JwtUtils.getMemberIdByJwtToken(request);        //返回用户id
        String role = appointmentService.getUserById(memberId);         //返回用户权限

        if (role.equals("实验室管理员")){      //身份为管理员一级
            boolean flag=appointmentService.refuseAppointmentFirst(uid);
            if (flag)
                return Result.ok();
            else
                return Result.fail("已经审核过了");
        }
        else if (role.equals("teacher")){  //身份为管理员二级
            appointmentService.refuseAppointmentSecond(uid);
            return Result.ok();
        }
        else {                //身份为管理员三级
            appointmentService.refuseAppointmentThird(uid);
            return Result.ok();
        }
    }


    //5.导出数据
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        appointmentService.exportAppointmentData(response);
    }

    //6.获取个人预约信息
    //需要分页
    @PostMapping("getAppointmentInfo/{current}/{limit}")
    public Result getAppointmentInfo(@PathVariable long current,
                                     @PathVariable long limit,
                                   HttpServletRequest request){

        Page<Appointment> page=new Page<>(current,limit);

        //构建查询条件
        QueryWrapper<Appointment> queryWrapper=new QueryWrapper<>();

        String userId= JwtUtils.getMemberIdByJwtToken(request);        //返回用户id
        queryWrapper.eq("user_id", userId);//查找该用户的预约信息


        //调用方法实现分页查询
        IPage<Appointment> usersPage=appointmentService.page(page,queryWrapper);

        //返回结果
        return  Result.ok(usersPage);


    }


    //7.用户取消预约
    //获取该行uid，删除
    //如果审核完成，则不能取消
    @PostMapping("deleteAppointment/{uid}")
    public Result deleteAppointment(@PathVariable Long uid){

        Appointment appointment = appointmentService.getById(uid);
        if (appointment.getStatus().equals("审核中")){                                 //只有在预约审核中的才能取消
            Appointment byId = appointmentService.getById(uid);
            byId.setStatus("已取消");

          //  appointmentService.update();
           appointmentService.removeById(uid);

            return Result.ok();
        }
        else
            return Result.fail();
        }


    //8.查看预约进度
    //1.获取该预约信息uid，查找到该信息
    //2.返回当前审核状态
    @PostMapping("searchProgess/{uid}")
    public Result searchProgess(@PathVariable Long uid) {

        Appointment appointment = appointmentService.getById(uid);     //找到该预约信息
        
        return Result.ok(appointment);

    }



}

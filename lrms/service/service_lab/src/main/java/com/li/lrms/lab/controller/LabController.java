package com.li.lrms.lab.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;
import com.li.lrms.lab.service.LabService;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.model.lab.Laboratory;
import com.li.lrms.model.labuse.Labuse;
import com.li.lrms.vo.lab.LabQueryVo;
import com.li.lrms.vo.labuse.LabuseQueryVo;
import com.li.lrms.vo.labuse.LabuseUpdateVo;
import com.li.lrms.vo.login.LoginVo;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.excel.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/lab")
public class LabController {
    @Autowired
    private LabService labService;

    //1.查询所有实验室
    @GetMapping("findAllLab")
    public Result findAllLab(){
        List<Laboratory> list = labService.list();
        return Result.ok(list);
    }
    //2.增加
    @PostMapping("addLab")
    public Result addLab(@RequestBody Laboratory laboratory){
        boolean b=labService.save(laboratory);
        if (b)
            return Result.ok();
        else
            return Result.fail();
    }

    //3.删除
    @DeleteMapping("{id}")
    public Result removeUser(@PathVariable String id){
        boolean b=labService.removeById(id);
        if (b)
            return Result.ok();
        else
            return Result.fail();
    }

    //4.条件查询分页
    @PostMapping("findpage/{current}/{limit}")
    public Result findPageLab(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) LabQueryVo labQueryVo){
        //创建page对象,传递当前页，每页记录数
        Page<Laboratory> page=new Page<>(current,limit);
        //构建查询条件
        QueryWrapper<Laboratory> queryWrapper=new QueryWrapper<>();

        String name= labQueryVo.getLabName();
        String function=labQueryVo.getFunc();

        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("lab_name", labQueryVo.getLabName()); //名称查询
        }
        if (!StringUtils.isEmpty(function)){
            queryWrapper.like("func", labQueryVo.getFunc()); //功能查询
        }

        //调用方法实现分页查询
        IPage<Laboratory> laboratoryIPage=labService.page(page,queryWrapper);

        //返回结果
        return  Result.ok(laboratoryIPage);

    }


    //5.按id查询
    @GetMapping("getLab/{id}")
    public Result getLab(@PathVariable String id){

        Laboratory laboratory=labService.getById(id);

        return Result.ok(laboratory);
    }

    //6.修改用户信息
    @PostMapping("updateLab")
    public Result updateLab(@RequestBody Laboratory laboratory){
        boolean update = labService.updateById(laboratory);
        if (update)
            return Result.ok();
        else
            return Result.fail();
    }

    //7.修改状态
    @PutMapping("updateStatus/{id}/{status}")
    public Result setStatus(@PathVariable String id,
                            @PathVariable Integer status){
        //根据id查询
        Laboratory byId = labService.getById(id);
        //修改信息
        byId.setStatus(status);
        //调用函数更新
        labService.updateById(byId);
        return  Result.ok();
    }

    //8.查询实验室空余时间
    @PostMapping("labFreeTime")
    public Result labFreeTime(@RequestBody LabuseQueryVo labuseQueryVo){   //返回实验室uid和查询日期

        Date appointDate = labuseQueryVo.getNowdate();   //预约日期
        String uid = labuseQueryVo.getUid();        //实验室uid
        //根据实验室uid获得实验室id
        String labId=labService.getLabID(uid);

        Labuse labuse=labService.searchFreeTime(labId,appointDate);   //返回该日实验室使用情况

        return Result.ok(labuse);

    }
    //9.实验室设备表
    @PostMapping("labEquip/{uid}")
    public Result labEquip(@PathVariable Long uid){         //获取该实验室uid
        List<Equipment> equipmentList=labService.selectEquipById(uid);

        //返回结果
        return  Result.ok(equipmentList);

    }



}

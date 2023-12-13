package com.li.lrms.equipment.controller;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;
import com.li.lrms.model.equipment.Equipment;
import com.li.lrms.equipment.service.EquService;
import com.li.lrms.vo.equipment.EquipmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/equipment")
public class EquipmentController {
@Autowired
    private EquService equService;

    //1.查询所有设备
    @GetMapping("findAll")
    public Result findAllEquipment(){
        List<Equipment> list = equService.list();
        return Result.ok(list);
    }
    //2.添加设备
    @PostMapping("addEquipment")
    public Result addEquipment(@RequestBody Equipment equipment){

        boolean b=equService.save(equipment);

        if (b)
            return Result.ok();
        else
            return Result.fail();
    }
    //3.删除设备
    @DeleteMapping("{uid}")
    public Result removeUser(@PathVariable Long uid){
        boolean b=equService.removeById(uid);
        if (b)
            return Result.ok();
        else
            return Result.fail();
    }
    //4.条件查询，分页
    @PostMapping("findpage/{current}/{limit}")
    public Result findPageUser(@PathVariable long current,
                               @PathVariable long limit,
                               @RequestBody(required = false) EquipmentVo equipmentVo){
        //创建page对象,传递当前页，每页记录数
        Page<Equipment> page=new Page<>(current,limit);

        //构建查询条件
        QueryWrapper<Equipment> queryWrapper=new QueryWrapper<>();
        String name=equipmentVo.getEquName();
        String id=equipmentVo.getEquId();


        if (!StringUtils.isEmpty(id)){     //输入非空
            queryWrapper.eq("equ_id", equipmentVo.getEquId());  //获取用户id
        }
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("equ_name", equipmentVo.getEquName()); //获取用户id
        }


        //调用方法实现分页查询
        IPage<Equipment> equipmentIPage=equService.page(page,queryWrapper);

        //返回结果
        return  Result.ok(equipmentIPage);

    }

    //5.按id查询
    @GetMapping("getEquipment/{uid}")
    public Result getEquipment(@PathVariable String uid){
         Equipment equipment=equService.getById(uid);
        return Result.ok(equipment);
    }

    //6.修改设备信息
    @PostMapping("updateEquipment")
    public Result updateEquipment(@RequestBody Equipment equipment){
        if (equipment.getEquName()==null){
            return Result.fail();
        }
        else{
            boolean flag=equService.updateById(equipment);

            if (flag)
                return Result.ok();
            else
                return Result.fail();
        }
    }

    //7.批量删除
    @DeleteMapping("batchRemove")
    public Result batchRemoveEquipment(@RequestBody List<String> list){
        equService.removeByIds(list);

        return Result.ok();
    }

    //8.更改状态
    @PutMapping("updateStatus/{uid}/{status}")
    public Result setStatus(@PathVariable String uid,
                            @PathVariable Integer status){
        //根据id查询
        Equipment equipment=equService.getById(uid);
        //修改信息
        equipment.setStatus(status);

        //调用函数更新
        equService.updateById(equipment);
        return  Result.ok();
    }



}

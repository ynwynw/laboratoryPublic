package com.li.lrms.labusetime.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.lrms.common.result.Result;
import com.li.lrms.labusetime.service.LabSchService;
import com.li.lrms.labusetime.service.LabuseService;
import com.li.lrms.model.labuse.LabScheduled;
import com.li.lrms.vo.labuse.LabuseUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/timeUpdate")
public class LabuseController {
    @Autowired
    private LabuseService labuseService;
    @Autowired
    private LabSchService labSchService;


    //1.实验室使用情况更新
    @PostMapping("updateLabuse")
    public Result updateLabuse(@RequestBody LabuseUpdateVo labuseUpdateVo) throws ParseException {
        //1.获取实验室的uid，找到实验室idds
        //2.获取当前时间日期
        //3.查找该实验室的所有时间安排。（一周）
        //4.如果存在一条时间晚于当前时间，则新增一条，内容为  周二--》周一  周三--》周二
        //5.删除时间晚的一条
        //Long uid = labuseUpdateVo.getUid();
        String today = labuseUpdateVo.getToday();
        //String labId = labuseService.getLabById(uid).getLabId();//得到实验室id
        labuseService.updateLab(today);         //更新实验室使用表
        return Result.ok();
    }
    //2.查看课表
    @GetMapping("searchLabSched/{uid}")
    public Result searchLabSched(@PathVariable  String uid){

        String labId= labSchService.selectByUid(uid);

        Page<LabScheduled> page=new Page<>(1,10);
        List<LabScheduled> labScheduled;
        switch (labId){
            case "1":
              labScheduled=labSchService.searchLab1Sched(labId);break;
            case "2":
                labScheduled=labSchService.searchLab2Sched(labId);break;
            case "3":
                labScheduled=labSchService.searchLab3Sched(labId);break;
            default:
                throw new IllegalStateException("Unexpected value: " + labId);
        }

       page.setRecords(labScheduled);

        //IPage<LabScheduled> labScheduledIPagePage=labSchService.page(page);

        return Result.ok(page);
    }

    //3.修改课表
    //1.输入实验室id，确定修改的表的名称
    //2.输入星期，确定修改哪一行
    //或者传回一个map，全部改一遍
    @PostMapping("updateLabSched")
    public Result updateLabSched(){return Result.ok();}
}

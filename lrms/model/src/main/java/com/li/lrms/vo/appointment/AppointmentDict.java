package com.li.lrms.vo.appointment;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class AppointmentDict {

    @ExcelProperty(value = "实验室id",index = 0)
    private String labId;

    @ExcelProperty(value = "实验室名称",index = 1)
    private String labName;

    @ExcelProperty(value = "用户id",index = 2)
    private Long userId;

    @ExcelProperty(value = "用户姓名",index = 3)
    private String userName;

    @ExcelProperty(value = "人数",index = 4)
    private Integer count;

    @ExcelProperty(value = "实验内容",index = 5)
    private String description;

    @ExcelProperty(value = "实验日期",index = 6)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointDate;

    @ExcelProperty(value = "实验时间段",index = 7)
    private Integer appointTime;

    @ExcelProperty(value = "是否通过",index = 8)
    private String status;

    @ExcelProperty(value = "实验室管理员意见",index = 9)
    private String checkFirst;

    @ExcelProperty(value = "实验中学意见",index = 10)
    private String checkSecond;

    @ExcelProperty(value = "学院意见",index = 11)
    private String checkThird;

}

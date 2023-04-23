package com.tq.springboot.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther: tq
 * @Date: 2022/9/28
 * @Description
 * @Version: 1.0
 */
@Data
public class ExaminationInfo {
    @TableId(type = IdType.AUTO)
    @ExcelProperty(value = "主键id", index = 0)
    private Integer id;
    @ExcelProperty(value = "试卷id", index = 1)
    private Integer examId;
    @ExcelProperty(value = "试卷类型", index = 2)
    private String tag;
    @ExcelIgnore
    private String difficulty;
    private Integer duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;
    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}

package com.tq.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tq.springboot.entity.ExaminationInfo;
import com.tq.springboot.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/9/28
 * @Description
 * @Version: 1.0
 */
@RestController
public class ExameController {
    @Autowired
    private ExameService exameService;

    @GetMapping("/exame/list")
    public List<ExaminationInfo> list(){
        return exameService.selectExameList();
    }

    @GetMapping("/exame/listByMybatisplus")
    public List<ExaminationInfo> list2(){
        return exameService.selectExameList2();
    }

    @GetMapping("/exame/pageList")
    public List<ExaminationInfo> pageList(){
        Page<ExaminationInfo> page = new Page<>(1,3);
         exameService.page(page,null);
        exameService.lambdaQuery().eq(ExaminationInfo::getTag,"算法");
        return page.getRecords();
    }

    @GetMapping("/exame/lambdaQuery")
    public List<ExaminationInfo> lambdaQuery(){
        List<ExaminationInfo> list = exameService.lambdaQuery().eq(ExaminationInfo::getTag, "算法").list();

        List<ExaminationInfo> list2 = exameService.lambdaQuery().eq(ExaminationInfo::getExamId, 9006).list();
        return list2;
    }

    @GetMapping("/exame/autoFill")
    public List<ExaminationInfo> autoFill(){
        //字段自动填充
        //1、使用 @TableField 注解，标注需要进行填充的字段
        //2、自定义一个类，实现 MetaObjectHandler 接口，并重写方法。 　　添加 @Component 注解，交给 Spring 去管理
        List<ExaminationInfo> list = exameService.lambdaQuery().eq(ExaminationInfo::getTag, "算法").list();
        ExaminationInfo e = new ExaminationInfo();
        e.setExamId(9006);
        e.setTag("高数");
        e.setDifficulty("hard");
        e.setDuration(70);
        e.setReleaseTime(LocalDateTime.now());
       // exameService.save(e);
        e.setId(7);
        exameService.updateById(e);

        return list;
    }

    @PostMapping("/exame/deleteIds")
    public void deleteIds(@RequestBody Integer[] ids){
        List<Integer> list = Arrays.asList(ids);
        exameService.removeByIds(list);

    }

}

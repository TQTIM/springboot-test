package com.tq.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tq.springboot.entity.DataRecord;
import com.tq.springboot.entity.ExaminationInfo;

import java.time.LocalDate;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/9/28
 * @Description
 * @Version: 1.0
 */
public interface ExameService extends IService<ExaminationInfo> {
    List<ExaminationInfo> selectExameList();
    List<ExaminationInfo> selectExameList2();

    List<DataRecord> selectDataRecord(LocalDate startDate,LocalDate endDate);

}

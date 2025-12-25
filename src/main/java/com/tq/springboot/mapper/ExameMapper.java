package com.tq.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tq.springboot.entity.DataRecord;
import com.tq.springboot.entity.ExaminationInfo;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Auther: tq
 * @Date: 2022/9/28
 * @Description
 * @Version: 1.0
 */

public interface ExameMapper extends BaseMapper<ExaminationInfo> {
    List<ExaminationInfo> selectExameList();

    List<DataRecord> selectDataRecord(@Param("startTime") LocalDateTime startDate, @Param("endTime")LocalDateTime  endDate);
}

package com.tq.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author tq
 * @date 2025/12/24 16:53
 * @description: 模拟报表数据
 */
@Data
public class DataRecord {
    private Long id;
    private String dataContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private BigDecimal amount;


}

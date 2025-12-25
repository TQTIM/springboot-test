package com.tq.springboot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.springboot.entity.DataRecord;
import com.tq.springboot.entity.ExaminationInfo;
import com.tq.springboot.mapper.ExameMapper;
import com.tq.springboot.service.ExameService;
import com.tq.springboot.utils.AesUtils;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @Auther: tq
 * @Date: 2022/9/28
 * @Description
 * @Version: 1.0
 */
@Service
public class ExameServiceImpl extends ServiceImpl<ExameMapper,ExaminationInfo> implements ExameService {
    @Autowired
    private ExameMapper exameMapper;
    @Override
    public List<ExaminationInfo> selectExameList() {
        List<ExaminationInfo> list = exameMapper.selectExameList();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ExcelWriter writer = EasyExcel.write(outputStream,ExaminationInfo.class).build();
        WriteSheet sheet = EasyExcel.writerSheet(0).build();
        writer.write(list, sheet);
        writer.finish();
        byte[] bytes = outputStream.toByteArray();

        String url = "http://localhost:8081/encdec/decrypt";
        String encryptStr = null;

        try {
            //转base64
            String baseFileStr = Base64FileUtil.encode(bytes);
            //System.out.println(baseFileStr);

            //aes加密
            encryptStr = AesUtils.encrypt(baseFileStr);
            //System.out.println(encryptStr);

            //http调用
            String s = HttpClientUtil.doPost(url, encryptStr);
            System.out.println(s);


        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<ExaminationInfo> selectExameList2() {
        List<ExaminationInfo> list = exameMapper.selectList(null);
        return list;
    }

    @Override
    public List<DataRecord> selectDataRecord(LocalDate startDate, LocalDate endDate) {
       // List<DataRecord> list = exameMapper.selectDataRecord();
        // 1️⃣ 参数校验（非常重要）
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("startDate 不能大于 endDate");
        }

        // 2️⃣ 拆分月份
        List<YearMonth> months = splitByMonth(startDate, endDate);
        //用线程池方式按月拆分报表
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 60,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        List<CompletableFuture<List<DataRecord>>> futures  = months.stream().map(month -> {
            CompletableFuture<List<DataRecord>> future = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "查询月份开始" + month);
                LocalDateTime monthStart =
                        month.atDay(1).atStartOfDay();
                LocalDateTime monthEnd =
                        month.plusMonths(1).atDay(1).atStartOfDay();
                return exameMapper.selectDataRecord(monthStart, monthEnd);
            }, executor);
            return future; //这里可简写直接返回也行，()-> CompletableFuture.supplyAsync(...) 或者month -> {return CompletableFuture.supplyAsync(...);}
        }).collect(Collectors.toList());


        // 4️⃣ 等待所有月份查询完成并收集结果
        CompletableFuture<Void> allDown = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        // 5️⃣ 合并结果 (其实可以和第四步链式写一起)
        CompletableFuture<List<List<DataRecord>>> allResults = allDown.thenApply(e -> futures.stream().map(CompletableFuture::join)
                .collect(Collectors.toList()));

        // 获取最终结果
        List<DataRecord> resultList = new ArrayList<>();
        try {
            List<List<DataRecord>> results = allResults.get();
            // 处理结果.扁平化合成想要的汇总返回
            resultList = results.stream()
                    .flatMap(List::stream) //List<DataRecord>
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }


        return resultList;
    }

    /**
     * 按月拆分
     */
    private List<YearMonth> splitByMonth(
            LocalDate startDate,
            LocalDate endDate) {
        List<YearMonth> result = new ArrayList<>();

        YearMonth current = YearMonth.from(startDate);
        YearMonth end = YearMonth.from(endDate);

        while (!current.isAfter(end)) {
            result.add(current);
            current = current.plusMonths(1);
        }
        return result;
    }


}

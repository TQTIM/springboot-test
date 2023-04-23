package com.tq.springboot.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.springboot.entity.ExaminationInfo;
import com.tq.springboot.mapper.ExameMapper;
import com.tq.springboot.service.ExameService;
import com.tq.springboot.utils.AesUtils;
import com.tq.springboot.utils.Base64FileUtil;
import com.tq.springboot.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

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

}

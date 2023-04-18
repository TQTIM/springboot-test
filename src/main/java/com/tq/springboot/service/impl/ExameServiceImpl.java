package com.tq.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tq.springboot.entity.ExaminationInfo;
import com.tq.springboot.mapper.ExameMapper;
import com.tq.springboot.service.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        return exameMapper.selectExameList();
    }

    @Override
    public List<ExaminationInfo> selectExameList2() {
        List<ExaminationInfo> list = exameMapper.selectList(null);
        return list;
    }

}

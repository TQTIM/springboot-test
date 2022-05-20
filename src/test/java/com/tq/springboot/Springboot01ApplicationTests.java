package com.tq.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot01ApplicationTests {

    @Test
    void contextLoads() {
        //springboot默认使用slf4j+logback日志
        Logger logger = LoggerFactory.getLogger(getClass());//获得记录器
        //日志级别由低到高：trace<debug<info<warn<error,springboot默认输出info级别之后的，可以在properties设置输出日志默认级别
        logger.trace("这时trace日志");
        logger.debug("这时debug日志");
        logger.info("这时info日志");
        logger.warn("这时warn日志");
        logger.error("这时error日志");

    }

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Test
    void mailTest() {//简单邮件测试发送

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();//简单邮件
        //设置邮件
        simpleMailMessage.setSubject("springboot测试邮件");
        simpleMailMessage.setText("测试邮件内容。。。");
        simpleMailMessage.setFrom("1330875414@qq.com");
        simpleMailMessage.setTo("qishixiangzuo@163.com");

        javaMailSender.send(simpleMailMessage);//发送

    }
    @Test
    void mailTest2() throws MessagingException {//测试复杂邮件发送
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);//后面参数为是否要上传文件
        //通过helper来设置邮件
        helper.setSubject("复杂邮件发送测试");
//        helper.setText("<b style='color:red'>测试复杂邮件内容。。可以用参数开启是否启用HTML</b>",true);
        helper.setText("<b style='color:red'>测试复杂邮件。。来自-其</b>",true);
        helper.setFrom("1330875414@qq.com");
        helper.setTo("qishixiangzuo@163.com");
 //       helper.setTo("1320656350@qq.com");


        //上传文件
        helper.addAttachment("1.jpg",new File("C:\\Users\\TQ\\Pictures\\Saved Pictures\\其.png"));//可写多个

        javaMailSender.send(mimeMessage);

    }
}

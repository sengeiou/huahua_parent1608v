package com.huahua.sms;

import com.huahua.sms.send.SmsSendCode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: SmsSend
 * @Author: zhangyuyang
 * @Time : 2019/4/24 -上午11:50
 * @Version:1.0
 */
@Component
@RabbitListener(queues = "sms")
public class SmsSend {

    /**
     * 拿取配置文件中的配置信息
     */
    @Value("${api.send.appCode}")
    public String appCode;

    @Value("${api.send.tplid}")
    private String tplid;


    /**
     * 发送短信
     */
    @RabbitHandler
    public void sendSms(Map<String,String> map ){
        System.out.println("手机号："+map.get("mobile"));
        System.out.println("验证码："+map.get("code"));
        //System.out.println("模拟--验证码发送成功");
        SmsSendCode.sendCode(appCode,map.get("mobile"),tplid,map.get("code"));
    }
}

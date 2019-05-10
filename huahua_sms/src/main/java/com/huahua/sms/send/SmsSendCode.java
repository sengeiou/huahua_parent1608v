package com.huahua.sms.send;

import com.huahua.sms.utils.HttpUtils;
import org.apache.http.HttpResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SmsSendCode
 * @Author: zhangyuyang
 * @Time : 2019/4/24 -下午4:22
 * @Version:1.0
 */
public class SmsSendCode {
    /**
     * 发送短信验证码
     * @param appcode 短信签名
     * @param mobile 手机号
     * @param tplId 模板id
     * @param code 验证码
     */
   public static void sendCode(String appcode,String mobile,String tplId,String code){
        String host = "http://dingxintz.market.alicloudapi.com";
        String path = "/dx/notifySms";
        String method = "POST";
      //  String appcode = "1700bd3f5847403fbe29e5ded27be5d2";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", mobile);
        querys.put("param", "name:"+code);
     //   querys.put("tpl_id", "TP18040315");
        querys.put("tpl_id", tplId);
        Map<String, String> bodys = new HashMap<String, String>();
        try {

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

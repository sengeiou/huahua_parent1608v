package com.huahua.qa.eureka;

import huahua.common.Result;
import huahua.common.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: LabelEureka
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -上午10:31
 * @Version:1.0
 */
//调用的是哪一个微服务
@FeignClient("huahua-base")
public interface LabelEureka {

    //value要写全路径
    @RequestMapping(method = RequestMethod.GET,value = "/label/{labelId}")
    public Result queryById(@PathVariable("labelId") String labelId);

}

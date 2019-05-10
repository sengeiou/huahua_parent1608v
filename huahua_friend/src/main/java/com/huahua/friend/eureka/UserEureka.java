package com.huahua.friend.eureka;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName: UserEureka
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -下午4:27
 * @Version:1.0
 */
@FeignClient("huahua-user")
public interface UserEureka {

    /**
     * 增加粉丝数
     */
    @PostMapping(value = "/user/incfans/{userid}/{num}")
    public void incfansCount(@PathVariable("userid")String userid, @PathVariable("num")Integer num);

    /**
     * 功能描述 :增加关注数(huahua-friend)
     * @author zhangyuyang
     * @date 2019/4/28
     * @param userid
     * @param num 数量
     * @return void
     */
    @PostMapping(value = "/user/incfollow/{userid}/{num}")
    public void incfollowCount(@PathVariable("userid")String userid,@PathVariable("num")Integer num);


    }

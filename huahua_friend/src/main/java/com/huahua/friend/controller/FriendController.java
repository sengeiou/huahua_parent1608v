package com.huahua.friend.controller;

import com.huahua.friend.service.FriendService;
import huahua.common.Result;
import huahua.common.StatusCode;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: FriendController
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -下午2:40
 * @Version:1.0
 */
@RestController
@RequestMapping("/friend")
@CrossOrigin
public class FriendController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private FriendService friendService;

    /**
      * 功能描述 :添加关注或者取消关注
      * @author zhangyuyang
      * @date 2019/4/28
     * @param friendid 被关注的用户id
     * @param type 1 喜欢  2 不喜欢
     * @return
     */
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addfriend(@PathVariable("friendid")String friendid,@PathVariable("type") String type){
        //判断值是否存在
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if (null ==claims ){
            return new Result(false, StatusCode.AUTOROLES,"无权访问");
        }
        //判断type类型是喜欢还是不喜欢
        //如果喜欢
        if (StringUtils.equals("1",type)){
           if (friendService.addFriend(claims.getId(),friendid)==0){
               return new Result(false, StatusCode.ERROR,"已关注用户");
           }
        }else{
            // 取消喜欢
            friendService.notLikeFrient(claims.getId(),friendid);
        }
        //返回状态
        return new Result(true, StatusCode.OK,"操作成功");
    }


    /**
     * 不喜欢（拉黑）
     */
    @DeleteMapping("/{friendid}")
    public Result delete(@PathVariable("friendid") String  friendid){
        Claims claims = (Claims) httpServletRequest.getAttribute("user_claims");
        if (null ==claims ){
            return new Result(false, StatusCode.AUTOROLES,"无权访问");
        }
        friendService.delete(claims.getId(),friendid);
        return new Result(true, StatusCode.OK,"操作成功");
    }

}

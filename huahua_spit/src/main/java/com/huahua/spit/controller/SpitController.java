package com.huahua.spit.controller;

import com.huahua.spit.entity.Spit;
import com.huahua.spit.service.SpitService;
import huahua.common.PageResult;
import huahua.common.Result;
import huahua.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName: SpitController
 * @Author: zhangyuyang
 * @Time : 2019/4/17 -下午3:29
 * @Version:1.0
 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    StringRedisTemplate redistemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return  new Result(true, StatusCode.OK,"查询成功",spitService.findAll());

    }
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Result findByid(@PathVariable String id){
        return  new Result(true, StatusCode.OK,"查询成功",spitService.findById(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return  new Result(true, StatusCode.OK,"增加成功");
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.update(spit);
        return  new Result(true, StatusCode.OK,"修改成功");
    }
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Result delete(@PathVariable String id){
        spitService.delete(id);
        return  new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 根据上级ID查询吐槽数据（分页）
     */
    @RequestMapping(method = RequestMethod.GET,value = "/comment/{parentid}/{page}/{size}")
    public Result findByPidList(@PathVariable("parentid") String  parentid,@PathVariable("page") Integer page,
                                @PathVariable("size") Integer size ){
        Page<Spit> spitList  = spitService.findByPidList(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(spitList.getTotalElements(),
                spitList.getContent() ));
    }

    /**
     * /thumbup/{spitId}
     * 吐槽点赞
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/thumbup/{spitId}")
    public Result  updateThumbup(@PathVariable("spitId") String  spitId){

        String userid = "10";
        //从缓存中 判断当前用户是否点过赞
        if (null != redistemplate.opsForValue().get("thumup_"+userid+"_"+spitId)){
            //如果缓存中存在数据，则代表该用户已经点过赞
            return new Result(true,StatusCode.OK,"你已经点过赞了");
        }
        //如果没有，则用户可以点赞，插入到缓存中
        spitService.updateThumbup(spitId);
        redistemplate.opsForValue().set("thumup_"+userid+"_"+spitId,"1");
        return new Result(true,StatusCode.OK,"点赞成功");





    }
}

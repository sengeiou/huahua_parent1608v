package com.huahua.spit.service;

import com.huahua.spit.dao.SpitDao;
import com.huahua.spit.entity.Spit;
import huahua.common.utils.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: SpitService
 * @Author: zhangyuyang
 * @Time : 2019/4/17 -下午3:41
 * @Version:1.0
 */
@Service
@Transactional
public class SpitService {
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpitDao spitDao;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 查询全部记录
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 通过id主键查询实体
     */
    public Spit findById(String  id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * 增加
     */
    public void add(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞回复数
        spit.setComment(0);//回复数
        spit.setState("1");

        //如果不为空 则修改parentId用户数据的Comment+1
        if (StringUtils.isNotEmpty(spit.getParentid())){
                Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        //判断parentId 是否为空， 如果为空  ，则是评论直接插入
        spitDao.save(spit);
    }

    /**
     * 修改
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }


    /**
     *删除
     */
    public void delete(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> findByPidList(String parentid, Integer page, Integer size) {
        PageRequest of = PageRequest.of(page - 1, size);
        Page<Spit> spitDaoByParentid = spitDao.findByParentid(parentid, of);

        return spitDaoByParentid;
    }


    public void updateThumbup(String spitId) {
        Query query = new Query();//创建查询语句
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update  = new Update();
       // update.addToSet();  update table set key = value where _id  = id
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}

package com.huahua.friend.service;

import com.huahua.friend.dao.FriendDao;
import com.huahua.friend.dao.NoFriendDao;
import com.huahua.friend.entity.Friend;
import com.huahua.friend.entity.NOFriend;
import com.huahua.friend.eureka.UserEureka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: FriendService
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -上午11:53
 * @Version:1.0
 */
@Service
public class FriendService {
    @Autowired
    FriendDao friendDao;

    @Autowired
    NoFriendDao noFriendDao;

    @Autowired
    UserEureka userEureka;

    /**
     * 关注喜欢的用户
     * @param userid
     * @param friendid
     * @return
     */
    @Transactional
    public int addFriend(String userid,String friendid){
        //判断用户已经添加了这个好友，则不进行任何操作，并且返回0
         //（select count(1) from tb_friend where userid = ? and  friend = ? ）>0 已经关注过了
        if (friendDao.selectByUserCount(userid,friendid)>0){
            return 0 ;
        }
        //如何没有添加  向喜欢表中添加记录
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        //判断对方是否也喜欢你，如何喜欢你，则双方islike 设置为1
        if (friendDao.selectByUserCount(friendid,userid)>0){ //如果大于0 则表示friendid用户也已经关注了 userid了
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");
        }
        //调用spring cloud 微服务 修改用户表的关注数，粉丝数
        // user A： 关注数：0  粉丝数：1
        //user B：关注数：1 粉丝数：0
        //首先是关注数A+1  和粉丝数b+1
        //user 修改的是关注数
        userEureka.incfollowCount(userid,1);
        //friendId修改是粉丝数
        userEureka.incfansCount(friendid,1);
        return 1;
    }

    @Transactional
    public void notLikeFrient(String userid, String friendid) {
        //删除用户数据
      //  friendDao.deleteById(userid);
        friendDao.deleteByuseridAndFriendid(userid,friendid);

        //判断是否互相关注
        //如果互相互粉  则修改friendid用户中 islike
        if (friendDao.selectByUserCount(friendid,userid)>0){
            friendDao.updateLike(friendid,userid,"0");
        }
        //调用spring cloud 微服务 修改用户表的关注数，粉丝数

        try {
            //user 修改的是关注数
            userEureka.incfollowCount(userid,-1);
            //friendId修改是粉丝数
            userEureka.incfansCount(friendid,-1);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Transactional
    public void delete(String userid,String friendid) {
        //判断当前用户是否喜欢过对方
        if (friendDao.selectByUserCount(userid,friendid)>0){
            //user 修改的是关注数
            userEureka.incfollowCount(userid,-1);
            //friendId修改是粉丝数
            userEureka.incfansCount(friendid,-1);
        }

        //删除我关注用户的那条记录
        friendDao.deleteByuseridAndFriendid(userid,friendid);
        //如何使互粉的情况下，拉黑的话， frirend islike 改为0
        if (friendDao.selectByUserCount(friendid,userid)>0){
            friendDao.updateLike(friendid,userid,"0");
        }
        //tb_nofriend 表中插入一条记录
        NOFriend noFriend = new NOFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);

    }
}

package com.huahua.friend.dao;

import com.huahua.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName: FriendDao
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -上午11:37
 * @Version:1.0
 */
public interface FriendDao extends JpaRepository<Friend,String> , JpaSpecificationExecutor<Friend> {

    /**
     * 更新为互相喜欢
     */
    @Modifying
    @Query(value = "update tb_friend set islike=?3 where userid = ?1 and friendid=?2",nativeQuery = true)
    public void updateLike(String userid,String friendid,String islike);

    /**
     * 效验用户是否关注了当前的用户
     */
    @Query(nativeQuery = true,value = "select count(1) from  tb_friend where friendid = ?2 and userid=?1")
    public int selectByUserCount(String userid,String friendid);

    @Modifying
    @Query(nativeQuery = true,value = "delete from tb_friend where userid=:userid and friendid=:friendid ")
    void deleteByuseridAndFriendid(@Param("userid")String userid,@Param("friendid") String friendid);
}

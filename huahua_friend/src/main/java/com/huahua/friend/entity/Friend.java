package com.huahua.friend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassName: Friend
 * @Author: zhangyuyang
 * @Time : 2019/4/28 -上午11:34
 * @Version:1.0
 */
@Entity
@Table(name = "tb_friend")
@Data
@IdClass(Friend.class)
public class Friend implements Serializable {

    private static final long serialVersionUID = 4326838658790141560L;

    @Id
    private String userid;
    @Id
    private String friendid;

    private String  islike;
}

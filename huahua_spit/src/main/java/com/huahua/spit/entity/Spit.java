package com.huahua.spit.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Spit
 * @Author: zhangyuyang
 * @Time : 2019/4/17 -下午3:35
 * @Version:1.0
 */
@Data
public class Spit implements Serializable {

    private static final long serialVersionUID = 923854195842574538L;
    @Id
    private String _id;

    private String content;

    private Date publishtime;

    private String userid;

    private String nickname;

    private Integer visits;

    private Integer thumbup;

    private Integer  share;

    private Integer  comment; //评论的次数

    private String  state;

    private String  parentid;
}

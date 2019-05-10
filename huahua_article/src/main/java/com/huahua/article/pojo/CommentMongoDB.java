package com.huahua.article.pojo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CommentMongoDB
 * @Author: zhangyuyang
 * @Time : 2019/4/18 -下午3:27
 * @Version:1.0
 */
@Data
public class CommentMongoDB implements Serializable {
    private static final long serialVersionUID = 1191474727362608831L;
    @Id
    private String  _id;
    private String  articleid;
    private String  content;
    private String  userid;
    private String  parentid;
    private Date    publishdate;

}

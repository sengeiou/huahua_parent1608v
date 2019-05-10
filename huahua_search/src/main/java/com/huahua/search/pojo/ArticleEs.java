package com.huahua.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @ClassName: ArticleEs
 * @Author: zhangyuyang
 * @Time : 2019/4/19 -下午4:56
 * @Version:1.0
 */
@Data
@Document(indexName = "huahua_article",type = "articleEs")
public class ArticleEs implements Serializable {
    private static final long serialVersionUID = 5076534605957319824L;

    @Id
    private String id ; //id 是对应我们数据库中id
    @Field(index= true  ,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;//标题
    @Field(index= true ,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String content;//文章正文
    private String state;//审核状态

}

package com.huahua.article.dao;

import com.huahua.article.pojo.CommentMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 评论Dao
 * 操作MongoDB
 */
public interface CommentMongoDBDao extends MongoRepository<CommentMongoDB,String> {

    List<CommentMongoDB> findAllByArticleidOrderByPublishdateDesc(String  articleid);
}

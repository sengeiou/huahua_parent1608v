package com.huahua.search.dao;

import com.huahua.search.pojo.ArticleEs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName: SearchDao
 * @Author: zhangyuyang
 * @Time : 2019/4/22 -上午10:53
 * @Version:1.0
 */
public interface SearchDao  extends ElasticsearchRepository<ArticleEs,String> {
    //select * from table where title = keywords or content = keywords 类似于
    Page<ArticleEs> findAllByTitleLikeOrContentLike(String title,String  content , Pageable pageable);

}

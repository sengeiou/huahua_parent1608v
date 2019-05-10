package com.huahua.search.service;

import com.huahua.search.dao.SearchDao;
import com.huahua.search.pojo.ArticleEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: SearchService
 * @Author: zhangyuyang
 * @Time : 2019/4/22 -上午10:52
 * @Version:1.0
 */
@Service
@Transactional
public class SearchService  {

    @Autowired
    private SearchDao searchDao;


    /**
     * 通过 title,content 查询es 引擎中的数据
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<ArticleEs> searchArticle(String keywords, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1, size);
        // select * from table where title = keywords or content = keywords
        return     searchDao.findAllByTitleLikeOrContentLike(keywords,keywords,pageRequest);
    }

    public void add(ArticleEs articleEs) {
        searchDao.save(articleEs);
    }
}

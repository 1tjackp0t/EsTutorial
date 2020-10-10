package com.ltjack.estutorial.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ltjack.estutorial.dao.UserRepository;
import com.ltjack.estutorial.pojo.BlogUser;
import com.ltjack.estutorial.service.UserService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description: User service实现类
 * @author: ltjack
 * @createTime: 2020-10-09 16:22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElasticsearchRestTemplate template;

    @Override
    public List<BlogUser> listUser(Map<String, String> params) {
        int from = 1;
        int pageSize = 20;
        if (params.containsKey("from") && params.containsKey("pageSize")) { // 配置分页
            from = Integer.parseInt(params.get("from"));
            pageSize = Integer.parseInt(params.get("pageSize"));
        }
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (params.containsKey("name")) { // 查询对象名
            queryBuilder.must(QueryBuilders.matchQuery("name", params.get("name")));
        }
        if (params.containsKey("hobby")) { // 查询对象能力
            String[] queryCaps = StrUtil.split(params.get("hobby"), ",");
            for (String query : queryCaps) {
                queryBuilder.must(QueryBuilders.constantScoreQuery(QueryBuilders.matchQuery("hobby", query)));
            }
        }
        List<BlogUser> resultList = new ArrayList<>();
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(PageRequest.of(from - 1, pageSize))
                .withQuery(queryBuilder)
                .build();
        SearchHits<BlogUser> hits = template.search(searchQuery, BlogUser.class);
        if (hits.getTotalHits() > 0) {
            resultList = hits.stream().map(SearchHit::getContent).collect(Collectors.toList());
            System.out.println(resultList);
        }
        return resultList;
    }

    @Override
    public Boolean addUser(BlogUser blogUser) {
        try {
            userRepository.save(blogUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateUser(BlogUser blogUser) {
        try {
            userRepository.save(blogUser);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteUser(String id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

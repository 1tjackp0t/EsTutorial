package com.ltjack.estutorial.dao;

import com.ltjack.estutorial.pojo.BlogUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<BlogUser, String> {
}
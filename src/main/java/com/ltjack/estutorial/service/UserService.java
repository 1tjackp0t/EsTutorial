package com.ltjack.estutorial.service;

import com.ltjack.estutorial.pojo.BlogUser;

import java.util.List;
import java.util.Map;

/**
 * @description: User service类
 * @author: ltjack
 * @createTime: 2020-10-09 16:21
 */
public interface UserService {

    // 查询&list
    List<BlogUser> listUser(Map<String, String> params);

    // 添加
    Boolean addUser(BlogUser blogUser);

    // 更新
    Boolean updateUser(BlogUser blogUser);

    // 删除
    Boolean deleteUser(String id);
}

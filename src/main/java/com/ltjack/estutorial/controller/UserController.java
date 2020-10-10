package com.ltjack.estutorial.controller;

import com.ltjack.estutorial.pojo.BlogUser;
import com.ltjack.estutorial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: User controller类
 * @author: ltjack
 * @createTime: 2020-10-09 16:16
 */
@RestController
@RequestMapping("/api/BlogUser")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public List<BlogUser> listBsItem(@RequestParam(required = false) Map<String, String> params) {
        System.out.println(params);
        return userService.listUser(params);
    }

    @PostMapping("add")
    public String addUser(@RequestBody BlogUser blogUser) {
        System.out.println(blogUser);
        if (userService.addUser(blogUser)) {
            return "添加成功"+"\n"+"用户ID:"+blogUser.getId();
        } else {
            return "添加失败";
        }
    }

//    @PostMapping("update")
    @PutMapping("update")
    public String updateUser(@RequestBody BlogUser blogUser) {
        System.out.println(blogUser);
        if (userService.updateUser(blogUser)) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

//    @GetMapping("delete/{id}")
    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable String id) {
        System.out.println(id);
        if (userService.deleteUser(id)) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}

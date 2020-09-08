package com.lz.read.controller;

import com.lz.read.common.RestResult;
import com.lz.read.pojo.User;
import com.lz.read.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :     lz
 * @date :       2020/4/3 20:33
 * description:
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list/{username}/{pageNum}/{pageSize}")
    public RestResult listUser(@PathVariable(required = false) String username, @PathVariable int pageNum, @PathVariable int pageSize) {
        if (log.isDebugEnabled()) {
            log.debug("分页查询用户。。");
        }
        return userService.listUser(username, pageNum, pageSize);
    }

    @PutMapping
    public RestResult updateUser(@RequestBody User user) {
        if (log.isDebugEnabled()) {
            log.debug("更新用户信息。。");
        }
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public RestResult delUser(@PathVariable Integer id) {
        if (log.isDebugEnabled()) {
            log.debug("删除用户。。");
        }
        return userService.delUser(id);
    }

    @PostMapping
    public RestResult addUser(@RequestBody User user) {
        if (log.isDebugEnabled()) {
            log.debug("添加用户。。");
        }
        return userService.addUser(user);
    }


}

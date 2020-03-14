package com.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.capisce.entity.UserInfoEntity;
import com.capisce.entrance.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxb
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Reference(version = "1.0.0")
    private IUserService userService;

    @RequestMapping(value = "createNewAccount", method = RequestMethod.POST)
    public Boolean createNewAccount(@RequestBody UserInfoEntity userInfoEntity){
        return userService.createNewAccount(userInfoEntity);
    }
}

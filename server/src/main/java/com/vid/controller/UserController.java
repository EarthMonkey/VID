package com.vid.controller;

import com.vid.model.User;
import com.vid.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Jiayiwu on 17/1/15.
 * Mail:wujiayi@lgdreamer.com
 * Change everywhere
 */

@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public List<User> getUsers(){

        return userService.getUsers();

    }

    @RequestMapping("/insert")
    @ResponseBody
    public String insert(User user){

        try {
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";

    }

}

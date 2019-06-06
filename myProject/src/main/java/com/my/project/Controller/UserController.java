package com.my.project.Controller;

import com.my.project.Service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://47.94.229.129:8080","http://localhost:8080"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public String login(String username,String password){
        if(null==username||"".equals(username)){
            return "usernameOrPasswordError";
        }
        if(null==password||"".equals(password)){
            return "usernameOrPasswordError";
        }
        int res = userService.getUser(username,password);
        if(res==0){
            return "usernameOrPasswordError";
        }
        return "ok";
    }
    @RequestMapping("tologin")
    public String tologin(String username,String password){
        if(null==username||"".equals(username)){
            return "usernameOrPasswordError";
        }
        if(null==password||"".equals(password)){
            return "usernameOrPasswordError";
        }
        int res = userService.toLogin( username,password);
        if(res!=1){
            return "usernameOrPasswordError";
        }
        return "ok";
    }
}

package com.example.action;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RegisterAction extends ActionSupport {
    private String name;
    private String loginId;
    private String password;
    private String tel;

    @Autowired
    private UserService userService;

    public String execute() {
        User user = new User();
        user.setName(name);
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setTel(tel);
        user.setCreateDate(new Date());

        userService.addUser(user);
        return SUCCESS; // 註冊成功跳回登入頁
    }

    // Getter/Setter ...
}

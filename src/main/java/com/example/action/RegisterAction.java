package com.example.action;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RegisterAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    @Autowired
    private UserService userService;

    @Override
    public String execute() {
        // 檢查帳號是否存在
        User exist = userService.findByLoginId(user.getLoginId());
        if (exist != null) {
            addActionError("帳號已存在，請使用其他使用者名稱");
            return INPUT;
        }

        user.setCreateDate(new Date());
        userService.addUser(user);

        // ✅ 提示訊息，配合 redirectAction preserveMessages 顯示在 login.jsp
        addActionMessage("註冊成功，請登入");
        return SUCCESS;
    }

    @Override
    public String input() {
        return SUCCESS;
    }

    @Override
    public void validate() {
        if (user.getLoginId() == null || user.getLoginId().trim().isEmpty()) {
            addFieldError("user.loginId", "使用者名稱不能為空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            addFieldError("user.password", "密碼不能為空");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            addFieldError("user.name", "姓名不能為空");
        }
        if (user.getTel() == null || user.getTel().trim().isEmpty()) {
            addFieldError("user.tel", "電話不能為空");
        }
    }

    @Override
    public User getModel() {
        return user;
    }

    public User getUser() {
        return user;
    }
}

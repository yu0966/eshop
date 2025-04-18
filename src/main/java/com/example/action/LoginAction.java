package com.example.action;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginAction extends ActionSupport {
    private String loginId;
    private String password;

    @Autowired
    private UserService userService;

    public String execute() {
        User inputUser = new User(loginId, password);
        User loginUser = userService.getLoginUser(inputUser);

        if (loginUser != null) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("user", loginUser);
            return SUCCESS; // 導向 list.jsp
        } else {
            addActionError("帳號或密碼錯誤");
            return LOGIN; // 回到 login.jsp
        }
    }

    // Getter/Setter
    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
 
}

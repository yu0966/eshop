package com.example.action;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.example.service.CartService;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginAction extends ActionSupport {
    private String loginId;
    private String password;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CartService cartService;

    public String execute() {
        User inputUser = new User(loginId, password);
        User loginUser = userService.getLoginUser(inputUser);

        if (loginUser != null) {
            HttpSession session = ServletActionContext.getRequest().getSession();
            session.setAttribute("user", loginUser);
            session.setAttribute("userId", loginUser.getId());
            
            // 獲取購物車商品數量
            int cartItemCount = cartService.getCartItemCount(loginUser.getId());
            session.setAttribute("cartItemCount", cartItemCount);
            
            return SUCCESS;
        } else {
            addActionError("帳號或密碼錯誤");
            return LOGIN;
        }
    }

    // Getters and Setters
    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
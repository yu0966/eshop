package com.example.action;

import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.example.service.CartService;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
	private String loginId;
    private String password;

    @Autowired
    private UserService userService;
    
    @Autowired
    private CartService cartService;

    public String execute() {
    	//登入畫面輸入的「帳號跟密碼」拿來，建立一個新的使用者物件 inputUser
        User inputUser = new User(loginId, password);
        //剛剛的建立的inputUser，透過service到資料庫裡查看看，有沒有這個人
        User loginUser = userService.getLoginUser(inputUser);

        if (loginUser != null) {
        	//開一個**Session（使用者專屬的小空間）**來記錄登入的資訊，瀏覽器之後就不用每一頁都重登一次
            HttpSession session = ServletActionContext.getRequest().getSession();
            //把這個登入成功的使用者整個物件，存進 Session，名字叫 "user"
            session.setAttribute("user", loginUser);
            //再存一個「使用者的 ID 編號」，名字叫 "userId"
            session.setAttribute("userId", loginUser.getId());
            
            // 再去查這個使用者的購物車裡有幾件商品？
            int cartItemCount = cartService.getCartItemCount(loginUser.getId());
            // 把這個購物車商品數量也一起存在 Session 裡
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
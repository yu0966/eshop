//這個檔案屬於 com.example.action 套件
package com.example.action;

import com.example.pojo.entity.User; // 使用者資料類別（帳號、密碼）
import com.example.service.UserService;// 使用者相關的服務（會呼叫 DAO 查資料）
import com.opensymphony.xwork2.ActionSupport;// Struts2 的基本動作類別（登入失敗時顯示錯誤訊息）

import javax.servlet.http.HttpSession;// 用來存取 session（像是登入後存使用者資訊）

import org.apache.struts2.ServletActionContext;// Struts 提供的工具，可以抓到目前的 request
import org.springframework.beans.factory.annotation.Autowired;// 自動注入物件（由 Spring 管理）
/*
 * 繼承 ActionSupport：
 * 表示這是一個可以被 Struts 執行的「動作類別（Action）」。
 * ActionSupport 提供了像 addActionError() 這種錯誤提示的功能。
 */
public class LoginAction extends ActionSupport {
	//使用者會在 login.jsp 輸入這兩個欄位（Struts 會自動幫你塞進來）
    private String loginId;
    private String password;

    //自動注入 UserService（由 Spring 幫你塞進來），告訴 Spring：「我需要 UserService，請自動幫我找來注入！
    @Autowired
    private UserService userService;

    /*使用者送出登入表單
     * 系統把輸入的帳密塞進一個 User 物件
     * 拿這個帳密去呼叫 UserService.getLoginUser(...) 查資料庫
     * 如果查得到，表示帳密正確 → 存進 session，登入成功
     * 如果查不到，帳密錯誤 → 顯示錯誤訊息，回到登入畫面
*/
    public String execute() {
        User inputUser = new User(loginId, password);// 把使用者輸入的帳密包成一個 User 物件
        User loginUser = userService.getLoginUser(inputUser);// 拿這個帳密去資料庫查看看

        if (loginUser != null) {
            HttpSession session = ServletActionContext.getRequest().getSession();// 拿到 session
            session.setAttribute("user", loginUser);// 把登入成功的使用者資料存進 session
            return SUCCESS; // 登入成功，跳轉到 list.jsp
        } else {
            addActionError("帳號或密碼錯誤");// 加一個錯誤訊息
            return LOGIN; // 回到 login.jsp
        }
    }

    // 提供 getter/setter（讓 Struts2 自動幫你對應表單欄位）
    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
 
}
//（Controller → Service → DAO → 資料庫）

/*為什麼要getter / setter
因為你是用 Struts2 做登入表單處理，而 Struts2 有一個「自動幫你塞值的功能」，
它會根據 表單欄位的 name 屬性 自動呼叫對應的 setter() 方法，把使用者輸入的資料塞進來！

Struts2 自動流程圖：
使用者輸入帳號密碼 → 送出表單 → 
Struts2 自動把欄位值塞到 Action 裡：
     loginId → setLoginId()
     password → setPassword()

setXXX()	接收表單資料	使用者送出表單時，Struts2 自動呼叫
getXXX()	顯示資料	需要顯示在 JSP 畫面上時，Struts2 會呼叫
*/
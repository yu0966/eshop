//這支程式放在 com.example.action，屬於「處理前端請求的地方」。
package com.example.action;
/*
 * 匯入這支程式會用到的類別：
 * User：使用者的資料結構
 * UserService：你寫好的服務層，負責新增或查詢使用者
 * ActionSupport：Struts2 提供的 Action 類別（內建 SUCCESS、INPUT 等常數）
 * ModelDriven：Struts2 的進階功能，讓你可以自動綁定表單資料到 user 物件
 * @Autowired：Spring 自動幫你注入 UserService，不用手動 new
 * Date：等一下我們要記錄使用者註冊的時間
 */
import com.example.pojo.entity.User;
import com.example.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//這是一個註冊用的 Action 類別
//ModelDriven<User> 表示會自動把表單資料綁定到一個 User 物件上
public class RegisterAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();//宣告一個 User 實體，Struts2 會自動幫你把表單資料塞進去（像是 loginId、password…）

//請 Spring 幫我們注入 UserService，這樣就可以呼叫它來新增使用者或查詢使用者
    @Autowired
    private UserService userService;

    @Override
    public String execute() {//註冊主要邏輯
    	//findByLoginId()	去資料庫找看看這個 loginId 是否已經註冊
        User exist = userService.findByLoginId(user.getLoginId());
        //if (exist != null)	如果找到帳號 → 顯示錯誤訊息並回到註冊畫面（INPUT）
        if (exist != null) {
            addActionError("帳號已存在，請使用其他使用者名稱");
            return INPUT;
        }
//user.setCreateDate(new Date())	設定註冊時間為現在
        user.setCreateDate(new Date());
        //userService.addUser(user)	呼叫 service 層把這個使用者存入資料庫
        userService.addUser(user);
//addActionMessage(...)	加上一句成功訊息
        addActionMessage("成功註冊！");
        //return "registered"	返回到註冊成功的頁面
        return "success";
    }
//input()：當初次點擊註冊頁面會執行
    @Override
    public String input() {
        return SUCCESS;
    }
/*
 * validate()：欄位驗證，送出前先幫你檢查
 * 這個方法在你按下「註冊」時會自動先跑一次
 * 如果有任何欄位沒填好，就會用 addFieldError() 顯示錯誤訊息，且不會跑 execute()！
 * 這樣就能確保表單送出前一定通過基本檢查。
    */
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
/*
 * getModel()：表單自動綁定關鍵
 * 因為我們實作了 ModelDriven<User>，代表：
 * Struts2 會自動把表單欄位塞到這個 User 物件裡面！
 * 所以在 register.jsp 裡只要這樣命名欄位：
 * <s:textfield name="loginId" />
 * <s:password name="password" />
 * Struts2 就會幫你自動把值塞到 user.loginId、user.password 裡面去 
 */
    @Override
    public User getModel() {
        return user;
    }
    /*
     * 幫助 JSP 讀資料
     * 這個是給 JSP 用歡迎：${user.name}
     */
    public User getUser() {
        return user;
    }
}
/*
全部流程（視覺化）
使用者打開註冊頁面 → input() → 顯示 register.jsp

使用者輸入資料後按送出 →
    ↓
Struts2 自動將資料塞入 user →
    ↓
呼叫 validate() 檢查欄位是否為空 →
    ↓
若欄位有錯 → 回到 register.jsp + 顯示錯誤
    ↓
若欄位沒錯 → 執行 execute():
    ↓
    查帳號是否存在？
        → 存在 → 顯示錯誤訊息，回到 INPUT
        → 不存在 → 設定註冊時間，寫入資料庫，顯示註冊成功頁面 
*/
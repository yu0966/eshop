// 宣告這個類別屬於 com.example.service.impl 套件
package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.pojo.entity.User;
import com.example.service.UserService;
/*
 * 匯入 Spring 的註解
 * @Autowired → 自動幫你把需要的物件（像是 DAO）準備好，不用手動 new 出來
 * @Service → 告訴 Spring：「這是一個服務層的元件」，系統會幫你管理這個類別（變成 Spring Bean）
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * public → 公開的，任何地方都可以使用這個類別
 * class → 宣告一個類別（class 是 Java 的基本單位）
 * UserServiceImpl → 這個類別的名字，Impl 是「實作 implementation」的意思
 * implements UserService → 表示「我來實作 UserService 介面裡面定義的方法」
*/
@Service
public class UserServiceImpl implements UserService {

	/*
	 * 注入 DAO（資料層），讓這個 Service 可以呼叫 DAO 來存取資料庫
	 * 
	 * @Autowired → 叫 Spring 自動幫你準備好 UserDao 的物件 private → 私有的，只有這個類別內部可以用 UserDao →
	 * 用來跟資料庫互動的工具 userDao → 變數名稱，你可以用這個名字呼叫方法（像 userDao.addUser(...)）
	 */
	@Autowired
	private UserDao userDao;

	/*
	 * 實作 UserService 裡面定義的 getLoginUser 方法，說明：呼叫 DAO 的方法，傳入
	 * user（包含帳號和密碼），從資料庫查出對應的使用者
	 * 
	 * @Override → 表示這是「實作介面裡的某個方法」 public → 方法是公開的 User → 這個方法會「回傳一個 User 物件」
	 * getLoginUser(User user) → 這個方法的名字是 getLoginUser，會接收一個 User 類型的參數（就是你輸入的帳號密碼）
	 * return userDao.getLoginUser(user); → 把資料交給 DAO 處理，然後回傳結果（如果帳號密碼正確，就會拿到資料）
	 */
	@Override
	public User getLoginUser(User user) {
		return userDao.getLoginUser(user);
	}

	/*
	 * 實作 addUser 方法：呼叫 DAO 的 addUser，把使用者資料新增到資料庫 void → 表示這個方法「不回傳任何東西」
	 * addUser(User user) → 接收一個 User 物件，把它加進資料庫 userDao.addUser(user); → 叫 DAO
	 * 去執行新增的動作
	 */
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	/*
	 * 實作 findByLoginId 方法：根據 loginId 查詢是否已有該帳號 findByLoginId(String loginId) →
	 * 接收一個帳號（字串） return userDao.findByLoginId(loginId); → 把帳號交給 DAO 查詢，看看是否已經存在
	 */
	@Override
	public User findByLoginId(String loginId) {
		return userDao.findByLoginId(loginId);
	}
}
//負責「操作資料庫」的動作。也就是說，UserDao 是讓我們的程式可以存取或查詢使用者資料的中介橋樑

//讓Java知道 這個檔案是在 com.example.dao 這個套件裡面
package com.example.dao;

//會用到 User 類別，所以要先 import 進來
import com.example.pojo.entity.User;

//宣告一個公開的介面 UserDao，只會定義方法名稱，不會有實作內容。真正的方法邏輯會在** UserDaoImpl**裡面完成
public interface UserDao {

	/*
	 * 這個方法是用來「查詢是否有符合帳號和密碼的使用者」： 傳入的參數是一個 User 物件，通常只會放入帳號（loginId）與密碼（password）。
	 * 回傳值是 User：如果找到，就回傳使用者資料；如果找不到，就回傳 null。 範例：
	 User inputUser =newUser("loginId123", "mypassword"); 
	 User loginUser =userDao.getLoginUser(inputUser); 
	 if (loginUser != null) { // 登入成功 } 
	 else {//登入失敗 }
	 */
	public User getLoginUser(User user);

	/* 
	 * 這個方法是「新增使用者資料到資料庫」。
	 * 傳入參數是一個完整的 User 物件（包含名字、帳號、密碼、電話、註冊時間等）
	 * 回傳 void：沒有回傳值，執行成功就表示新增成功（失敗會丟出例外）舉例：
	 * User newUser = new User();
		newUser.setLoginId("abc123");
		newUser.setPassword("pw123");
		newUser.setName("小明");
		newUser.setTel("0912345678");
		newUser.setCreateDate(new Date());
		userDao.addUser(newUser); // 儲存到資料庫
	 */
	public void addUser(User user);
	/**
	 * 這個方法是「用帳號查詢使用者」。
	 * 傳入帳號（loginId）這個字串。
	 * 回傳 User 物件：如果找得到，就回傳資料；找不到就回傳 null。
	 * 註冊帳號時先檢查「帳號是否已被使用」
	 * 登入系統時查詢該帳號的資料。舉例：
	 * User newUser = new User();
		newUser.setLoginId("abc123");
		newUser.setPassword("pw123");
		newUser.setName("小明");
		newUser.setTel("0912345678");
		newUser.setCreateDate(new Date());
		userDao.addUser(newUser); // 儲存到資料庫
	 */
	public User findByLoginId(String loginId);
}
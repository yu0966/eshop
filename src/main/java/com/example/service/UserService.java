package com.example.service;

//這行是「匯入」User 類別，表示等一下會用到 User 這個類別，它定義在 com.example.pojo.entity 套件裡。就像你要用別的資料夾裡的東西，先打開再使用。
import com.example.pojo.entity.User;

/**
 * 宣告了一個 UserService 介面。 interface 是 Java
 * 中的「規格表」或「合約」，告訴大家：「誰實作這個介面，就一定要寫出介面裡面定義的方法！」
 */
public interface UserService {

	/*
	 * 使用者登入 「根據傳進來的 user（含帳號與密碼），去找看看資料庫有沒有這個人，有的話就回傳整個 User 資料，沒有就回傳 null。」
	 * 參數說明：User user：傳進來的 user 物件裡面有帳號、密碼。 回傳型別：User：如果找得到這個人，就把對應的 User 資料回傳。
	 */
	public User getLoginUser(User user);

	/*
	 * 新增一個新的使用者 參數說明：User user：裡面放著你要新增的使用者資訊（例如：帳號、密碼、電話等等）。
	 * void：表示這個方法不回傳任何東西，只是去做「新增」這件事。
	 */
	public void addUser(User user);

	// 根據 loginId 查詢使用者資料（通常用來註冊時檢查帳號是否已存在）
	public User findByLoginId(String loginId);
}
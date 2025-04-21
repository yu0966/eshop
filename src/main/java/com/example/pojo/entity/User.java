//這個類別是用來表示「使用者」的資料模型，也就是標準的 Java Bean 或 POJO（Plain Old Java Object）

//宣告這個類別的套件位置（package）。就像檔案夾分類一樣，說明 User 這個類別是在 com.example.pojo.entity 這個資料夾路徑下
package com.example.pojo.entity;

//匯入 Java 的內建 Date 類別，用來處理時間與日期，等一下會用在使用者註冊日期的欄位上
import java.util.Date;

//定義一個公開的類別，名字叫 User。這個類別就代表「使用者」的物件
public class User {

	/*
	 * 以下是使用者的各個屬性（欄位）：private意思是「這個欄位只能在這個類別裡使用，外面不能直接操作」，
	 * 為了安全性與封裝，我們希望使用者只能透過「方法」來存取資料。 這時候就要用兩種方法：Getter：取得值（Get） Setter：設定值（Set）
	 */
	private String id;

	private String name;

	private String loginId;

	private String password;

	private String tel;

	private Date createDate;

	// 建構子（Constructor），是在建立物件的時候呼叫的「初始化方法」，無參數的建構子，代表你可以這樣寫：User u = new User();
	public User() {
	}

	// 這是有兩個參數的建構子，代表你可以建立物件時順便給帳號和密碼：也可這麼寫User u = new User("john123", "pass456");
	public User(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	/*
	 * Getter 方法，它的名稱是 getId()，意思是「取得 id 的值」就像是 User user = new User(); String myId
	 * = user.getId(); // 把 user 裡面的 id 取出來放到 myId 變數
	 */
	public String getId() {
		return id;
	}

	/*
	 * Setter 方法，它的名稱是 setId()，用來設定 id 的值就像是 User user = new User();
	 * user.setId("U001"); // 把 user 的 id 設成 "U001" this.id = id; 這裡的 this.id
	 * 是物件本身的欄位，右邊的 id 是從方法參數傳進來的值。這樣寫是為了區分兩個 id。
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
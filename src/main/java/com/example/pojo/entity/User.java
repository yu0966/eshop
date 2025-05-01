package com.example.pojo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity // 表示這是一個 Hibernate 實體類別
@Table(name = "users") // 對應到資料庫中的 "users" 資料表
public class User {

	@Id // 主鍵
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自動遞增
	@Column(name = "id") // 對應到資料表欄位
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "login_id", unique = true, nullable = false)
	private String loginId;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "tel")
	private String tel;

	@Temporal(TemporalType.TIMESTAMP) // 設定時間格式
	@Column(name = "create_date")
	private Date createDate;

	public User() {
	}

	public User(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public String getId() {
		return id;
	}

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

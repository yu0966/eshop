package com.example.action;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	/*
	 * 這是 Java 中的註解，表示這個方法是從父類別或接口中重寫過來的。 方法是覆寫了父類別或接口中的 execute() 方法。
	 */
	@Override
	/*
	 * throws Exception （會丟例外）表示這個方法可能會拋出一個 Exception 類型的異常。 Exception 是
	 * Java中最常見的異常類型，它是所有異常類型的父類（也就是大部分異常的根本類型）。
	 * 這表示這個方法可能會遇到一些錯誤，比如連接資料庫出錯、文件讀取出錯、或者某些預期之外的問題。 當你使用 throws Exception
	 * 時，你是在告訴編譯器： "這個方法有可能會發生錯誤，這些錯誤不會在方法內部被處理，會交給調用這個方法的地方來處理。"
	 */
	public String execute() throws Exception {
		/*
		 * 取得當前的 HTTP 請求，然後從中獲取當前的 session（會話）。 false 參數的意思是，如果當前沒有 session，就返回
		 * null，而不是創建一個新的 session。
		 */
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		// 如果 session 不為空，也就是用戶確實已經登入過，才會進行登出操作
		if (session != null) {
			// 這行代碼是用來使當前的 session 失效，這樣用戶的登錄狀態就會被清除，實現登出效果
			session.invalidate();
		}

		return "login";
	}
}

package com.example.action;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// 取得當前 Session 並清除
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// 返回 login 導向（Struts.xml 會定義去 login.jsp）
		return "login";
	}
}

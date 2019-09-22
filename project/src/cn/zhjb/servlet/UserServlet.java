package cn.zhjb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhjb.pojo.User;
import cn.zhjb.service.UserService;
import cn.zhjb.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	UserService userService = new UserServiceImpl();

	/**
	 * 处理登录请求的方法
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void userLogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// 获取数据
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");

		// 处理数据
		User user = userService.getUserInfoService(username, userpwd);

		HttpSession session = req.getSession();
		// 响应结果
		if (user != null) {
			session.setAttribute("user", user);
			resp.sendRedirect("/project/main.jsp");
		} else {
			session.setAttribute("loginFlag", "loginFalse");
			resp.sendRedirect("/project/login.jsp");
		}
	}
	
	/**
	 * 处理用户退出请求的方法
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//处理请求 销毁session
		HttpSession session = req.getSession();
		session.invalidate();
				
		//响应
		resp.sendRedirect("/project/login.jsp");
	}
}

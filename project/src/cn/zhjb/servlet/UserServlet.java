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
	 * �����¼����ķ���
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void userLogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// ��ȡ����
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");

		// ��������
		User user = userService.getUserInfoService(username, userpwd);

		HttpSession session = req.getSession();
		// ��Ӧ���
		if (user != null) {
			session.setAttribute("user", user);
			resp.sendRedirect("/project/main.jsp");
		} else {
			session.setAttribute("loginFlag", "loginFalse");
			resp.sendRedirect("/project/login.jsp");
		}
	}
	
	/**
	 * �����û��˳�����ķ���
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//�������� ����session
		HttpSession session = req.getSession();
		session.invalidate();
				
		//��Ӧ
		resp.sendRedirect("/project/login.jsp");
	}
}

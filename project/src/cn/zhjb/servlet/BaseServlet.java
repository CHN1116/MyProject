package cn.zhjb.servlet;


import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//���ñ����ʽ
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//�������ķ�����
		String methodName = req.getParameter("method");
		//����������󡢶�Ӧ�ķ���
		try{
			Class cla = this.getClass();
			Method method = cla.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,req,resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.User;
/**
 * �����û��ĵǳ�
 * @author �̺�
 *
 */
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//��ȡ��ǰ��¼���û�
		HttpSession session = request.getSession();
		if((User)session.getAttribute(User.SESSION_NAME)!=null){
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath()+"/login");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

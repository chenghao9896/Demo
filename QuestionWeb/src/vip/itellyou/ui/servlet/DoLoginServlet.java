package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.pojo.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;
import vip.itellyou.util.exception.RuleException;
import vip.itellyou.util.format.Md5Class;

public class DoLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String flag = request.getParameter("remember");
		
		User user = new User();
		user.setName(name);
		user.setPwd(Md5Class.stringToMd5(pwd));
		try {
			UserService service = new UserServiceImpl();
			service.verify(user);
			request.getSession().setAttribute("name", user.getName());
			if("1".equals(flag)){
				String namepwd = name + "-" + Md5Class.stringToMd5(pwd);
				//�½�һ��cookie����
				//������URLEncoder.encode
				namepwd = URLEncoder.encode(namepwd, "utf-8");
				Cookie cookie = new Cookie("userinfo", namepwd);
				//���ù���ʱ�� 10*24*60*60(10��)
				cookie.setMaxAge(10*24*60*60);
				//����cookie���ͻ�
				response.addCookie(cookie);
			}
			response.sendRedirect(request.getContextPath()+"/vote");
		} catch (RuleException re) {
			request.setAttribute("user", user);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		} catch (Exception e){
			//ͳһ�쳣����
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

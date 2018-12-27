package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String remember = request.getParameter("remember");
		//实体类对象
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setRemember(remember);
		//2 创建业务逻辑对象，调用业务方法
		try {
			UserService service = new UserServiceImpl();
			user = service.verify(user);
			HttpSession session = request.getSession();
			session.setAttribute(User.SESSION_NAME, user);
			if(remember!=null){
//				String namepwd = name + "-" + Md5Class.stringToMd5(pwd);
//				//新建一个cookie对象
//				//编码用URLEncoder.encode
//				namepwd = URLEncoder.encode(namepwd, "utf-8");
				Cookie cookie = new Cookie(User.SESSION_NAME, user.getId().toString());
				//设置过期时间 10*24*60*60(10天)
				cookie.setMaxAge(10*24*60*60);
				//保存cookie到客户
				response.addCookie(cookie);
			}
		//3 衔接jsp refer 防盗链
		//重定向到投票项目列表界面 /list
			response.sendRedirect(request.getContextPath()+"/list");
		} catch (RuleException re) {
			request.setAttribute("user", user);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		} catch (Exception e){
			//统一由全局的异常处理过滤器来处理
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

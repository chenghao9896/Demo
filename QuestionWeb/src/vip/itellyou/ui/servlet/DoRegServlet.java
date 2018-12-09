package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.pojo.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;
import vip.itellyou.util.exception.RuleException;

public class DoRegServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取浏览器提交的数据
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirmPwd");
		
		// 实体类才是数据的载体
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		user.setConfirmPwd(confirmPwd);
		
		try {
			//2 创建业务逻辑对象，调用业务方法处理数据
			UserService service = new UserServiceImpl();
			service.register(user);
			
			//3 衔接JSP：重定向到/login登录页面
			response.sendRedirect(request.getContextPath()+"/login");
		} catch (RuleException re) {
			
			request.setAttribute("user", user);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/reg.jsp").forward(request, response);
		} catch (Exception e){
			//统一异常处理
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

package vip.itellyou.ui.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;
/**
 * 登录过滤器
 * @author 程浩
 *
 */
public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// 查找cookie中的值，不存在用户则需要手动登录，如果存在，用户自动登陆
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		if(session.getAttribute(User.SESSION_NAME)==null) {
			//读取cookie中的用户id
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for (Cookie cookie : cookies) {
					if(User.SESSION_NAME.equals(cookie.getName())){
						try {
							int id = Integer.parseInt(cookie.getValue());
							UserService userservice = new UserServiceImpl();
							User user = userservice.getUser(id);
							userservice.setOnline(user, false);
							session.setAttribute(User.SESSION_NAME, user);
							break;
						}  catch (Exception e) {
							throw new RuntimeException();
						}
					}
				}
			}
			}
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
////解码用URLDecoder.decode
//String namepwd = URLDecoder.decode(cookie.getValue(), "utf-8");
//String name = namepwd.split("-")[0];
//String pwd = namepwd.split("-")[1];

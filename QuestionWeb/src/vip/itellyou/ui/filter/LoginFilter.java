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

import vip.itellyou.dao.impl.UserDaoImpl;
import vip.itellyou.pojo.User;
import vip.itellyou.pojo.UserQueryModel;
/**
 * ��¼������
 * @author �̺�
 *
 */
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// ����cookie�е�ֵ���������û�����Ҫ�ֶ���¼��������ڣ��û��Զ���½
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if("userinfo".equals(cookie.getName())){
				//������URLDecoder.decode
				String namepwd = URLDecoder.decode(cookie.getValue(), "utf-8");
				String name = namepwd.split("-")[0];
				String pwd = namepwd.split("-")[1];
				try {
					UserDaoImpl userDao = new UserDaoImpl();
					UserQueryModel qm = new UserQueryModel();
					qm.setName(name);
					List list = userDao.findByCondition(qm);
					User user = (User)list.get(0);
					if(pwd.equals(user.getPwd())){
						req.setAttribute("name", name);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

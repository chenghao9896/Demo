package vip.itellyou.ui.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import vip.itellyou.pojo.User;
import vip.itellyou.service.UserService;
import vip.itellyou.service.impl.UserServiceImpl;

public class SetOnlineListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		User user = (User)session.getAttribute(User.SESSION_NAME);
		UserService userService = new UserServiceImpl();
		userService.setOnline(user, true);
	}

}

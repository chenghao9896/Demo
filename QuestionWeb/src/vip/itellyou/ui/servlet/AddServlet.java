package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;

public class AddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute(User.SESSION_NAME)==null) {
			response.sendRedirect(request.getContextPath()+"/login");
		}
		try {
			String id = request.getParameter("id");
			if(!"".equals(id)){
				SubjectService subjectService = new SubjectServiceImpl();
				Subject subject = new Subject();
				subject = subjectService.getOneSubject(Integer.parseInt(request.getParameter("id")));					
				request.setAttribute("subject", subject);
			}
		} catch (Exception e) {
			
		}
		request.getRequestDispatcher("/WEB-INF/pages/add.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
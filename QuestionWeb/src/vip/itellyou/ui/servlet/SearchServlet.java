package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vip.itellyou.pojo.Subject;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;

public class SearchServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String s = request.getParameter("keywords");
			SubjectService subjectService = new SubjectServiceImpl();
			List<Subject> subjectList = new ArrayList<Subject>();
			subjectList = subjectService.getByStringSubjects(s);
			request.setAttribute("subjectList", subjectList);
			request.getRequestDispatcher("/WEB-INF/pages/list.jsp").forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

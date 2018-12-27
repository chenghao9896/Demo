package vip.itellyou.ui.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.Record;
import vip.itellyou.pojo.User;
import vip.itellyou.service.RecordService;
import vip.itellyou.service.impl.RecordServiceImpl;
import vip.itellyou.util.exception.RuleException;

public class DoVoteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//1、获取表单中的数据
		String subjectId = request.getParameter("subjectId");
		String[] optionsId = request.getParameterValues("options");
		//获取当前用户的id
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(User.SESSION_NAME);
		Long userId = user.getId();
		Record record = new Record();
		record.setSubjectId(new Long(subjectId));
		record.setOptionsId(optionsId);
		record.setUserId(new Long(userId));
		//2 创建业务逻辑对象，调用业务方法处理数据
		RecordService recordService = new RecordServiceImpl();
		try{
			recordService.add(record);
			//重定向到投票项目列表界面 /list
			response.sendRedirect(request.getContextPath()+"/list");
		} catch(RuleException re) {
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/m/vote?id="+subjectId).forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

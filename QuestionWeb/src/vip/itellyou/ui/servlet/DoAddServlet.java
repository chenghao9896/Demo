package vip.itellyou.ui.servlet;
/**
 * //处理表单中填写的项目标题等数据，完成发起调查项目
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vip.itellyou.pojo.Option;
import vip.itellyou.pojo.Subject;
import vip.itellyou.pojo.User;
import vip.itellyou.service.SubjectService;
import vip.itellyou.service.impl.SubjectServiceImpl;
import vip.itellyou.util.exception.RuleException;
import vip.itellyou.util.format.StrFormatNum;

public class DoAddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 获取提交数据
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String number = request.getParameter("number");
		//一个表单中可以有多个表单元素同名
		//接收一个name多个value使用getParameterValues方法
		String[] options = request.getParameterValues("options");
		//创建主题对象
		Subject subject = new Subject();
		subject.setTitle(title);
		for (String content : options) {
			Option option = new Option();
			option.setContent(content);
			//主题中的选项集合中加入选项
			subject.getOptions().add(option);
		}
		//2 创建业务逻辑对象，调用业务方法处理数据
		HttpSession session = request.getSession();
		SubjectService subjectService = new SubjectServiceImpl();
		try {
			if("".equals(id)){
				//空串转化会报错
				subject.setNumber(StrFormatNum.toInt(number));
				subjectService.add(subject, (User)session.getAttribute(User.SESSION_NAME));
			}else{
				//不会出现空串
				subject.setId(new Long(id));
				subject.setNumber(Integer.parseInt(number));
				subjectService.updateSubject(subject);
			}
			//衔接jsp
			response.sendRedirect(request.getContextPath()+"/list");
		} catch (RuleException re) {
			//数据回显
			request.setAttribute("subject", subject);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/add.jsp").forward(request, response);
		}catch (Exception e) {
			//交给全局的异常处理过滤器
			throw new RuntimeException(e);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

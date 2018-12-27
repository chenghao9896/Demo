package vip.itellyou.ui.servlet;
/**
 * //���������д����Ŀ��������ݣ���ɷ��������Ŀ
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
		//1 ��ȡ�ύ����
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String number = request.getParameter("number");
		//һ�����п����ж����Ԫ��ͬ��
		//����һ��name���valueʹ��getParameterValues����
		String[] options = request.getParameterValues("options");
		//�����������
		Subject subject = new Subject();
		subject.setTitle(title);
		for (String content : options) {
			Option option = new Option();
			option.setContent(content);
			//�����е�ѡ����м���ѡ��
			subject.getOptions().add(option);
		}
		//2 ����ҵ���߼����󣬵���ҵ�񷽷���������
		HttpSession session = request.getSession();
		SubjectService subjectService = new SubjectServiceImpl();
		try {
			if("".equals(id)){
				//�մ�ת���ᱨ��
				subject.setNumber(StrFormatNum.toInt(number));
				subjectService.add(subject, (User)session.getAttribute(User.SESSION_NAME));
			}else{
				//������ֿմ�
				subject.setId(new Long(id));
				subject.setNumber(Integer.parseInt(number));
				subjectService.updateSubject(subject);
			}
			//�ν�jsp
			response.sendRedirect(request.getContextPath()+"/list");
		} catch (RuleException re) {
			//���ݻ���
			request.setAttribute("subject", subject);
			request.setAttribute("message", re.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/add.jsp").forward(request, response);
		}catch (Exception e) {
			//����ȫ�ֵ��쳣���������
			throw new RuntimeException(e);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}

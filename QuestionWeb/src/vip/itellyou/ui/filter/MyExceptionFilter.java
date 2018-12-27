package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * ȫ�ֵ��쳣���������
 * @author �̺�
 *
 */
public class MyExceptionFilter implements Filter {
	
	private String errorPage;
//	private String fileName;
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(req, res);
		} catch (Exception e) {
			//��¼��־ log4j
			//��ת�����������
			e.printStackTrace();
			req.getRequestDispatcher("/WEB-INF/pages" + this.errorPage).forward(req, res);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//��ʼ��������������ҳ��ĵ�ַ����־�ļ����ļ���
		this.errorPage = config.getInitParameter("errorPage");
//		this.fileName = config.getInitParameter(fileName);

	}

}

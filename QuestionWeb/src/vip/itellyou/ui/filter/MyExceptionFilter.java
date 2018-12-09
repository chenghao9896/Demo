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
	private String fileName;
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
			req.setAttribute("ex", e);
			req.getRequestDispatcher(this.errorPage).forward(req, res);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//��ʼ��������������ҳ��ĵ�ַ����־�ļ����ļ���
		this.errorPage = arg0.getInitParameter(errorPage);
		this.fileName = arg0.getInitParameter(fileName);

	}

}

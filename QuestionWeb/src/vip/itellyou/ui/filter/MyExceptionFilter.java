package vip.itellyou.ui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 全局的异常处理过滤器
 * @author 程浩
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
			//记录日志 log4j
			//跳转到错误处理界面
			req.setAttribute("ex", e);
			req.getRequestDispatcher(this.errorPage).forward(req, res);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//初始化参数：错误处理页面的地址，日志文件的文件名
		this.errorPage = arg0.getInitParameter(errorPage);
		this.fileName = arg0.getInitParameter(fileName);

	}

}

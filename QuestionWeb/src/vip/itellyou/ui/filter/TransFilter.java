package vip.itellyou.ui.filter;
/**
 * 事务处理和关闭连接的过滤器
 */
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import vip.itellyou.util.dao.DbHelper;

public class TransFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			// 1 开启事务
			DbHelper.beginTransaction();
			// 2 放行
			chain.doFilter(req, res);
			// 3 提交事务
			DbHelper.commitTransaction();
		} catch (Exception e) {
			try {
				// 回滚事务
				DbHelper.rollbackTransaction();
			} catch (Exception e1) {
				// 交给全局的异常处理器
				throw new RuntimeException(e1);
			}
			// 交给全局的异常处理器
			throw new RuntimeException(e);
		}
		finally{
			try {
				//关闭数据库连接
				DbHelper.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

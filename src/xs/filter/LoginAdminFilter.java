/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xs.filter;

import inet.bean.Admin;
import inet.constant.Constants;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Designer Nguyễn
 */
public class LoginAdminFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		Admin adminOnl = null;
		try {
			adminOnl = (Admin) session.getAttribute(Constants.ADMIN_LOGIN_SESSION);
		} catch (Exception ex) {
		}
		if (adminOnl == null && !uri.toLowerCase().contains("/admin/login/index.htm")) {
			response.sendRedirect(request.getContextPath() + "/admin/login/index.htm");
		} else
			chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}
}

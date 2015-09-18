package filter;

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



public class ServletSecurityFilter implements Filter {
	private String indexPath;

	public void init(FilterConfig fConfig) throws ServletException {
		indexPath = fConfig.getInitParameter("SERVLET_PATH");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String userID = (String) session.getAttribute("id");
		String paramCommand = request.getParameter("command");

		if (paramCommand == null) {
			resp.sendRedirect(req.getContextPath() + indexPath);
		} else if (paramCommand.compareTo("login") == 0
				|| paramCommand.compareTo("registration") == 0
				|| paramCommand.compareTo("make_registration") == 0) {
			chain.doFilter(request, response);
		} else if (userID != null) {

			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + indexPath);
		}

	}

}

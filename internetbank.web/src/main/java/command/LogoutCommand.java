package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bundle.Constants;

public class LogoutCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		 request.getSession().invalidate();
	     return Constants.PAGE_INDEX;
	}

}

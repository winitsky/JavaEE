package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bundle.Constants;

public class RegistrationCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return Constants.PAGE_REGESTRATION;
	}

}

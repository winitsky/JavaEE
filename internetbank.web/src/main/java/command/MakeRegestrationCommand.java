package command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import entity.User;
import service.UserService;
import bundle.Constants;
import validate.ValidateData;

public class MakeRegestrationCommand implements Command {
	static Logger logger = Logger.getLogger(MakeRegestrationCommand.class);
	/*static {
		new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
		}
	static Logger logger = Logger.getLogger(MakeRegestrationCommand.class);*/
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		boolean checkFormField = false;
		String path = Constants.PAGE_REGESTRATION;
		UserService userService = new UserService();
		HttpSession session = request.getSession();
		String login="";
		String password="";
		String name="";
		String surname="";
		int account=0;
		int balance=0;
		int role=0;

		if (ValidateData.checkLogin(request.getParameter("login"))
				&& (ValidateData
						.checkPassword(request.getParameter("password")))
				&& (ValidateData.checkNameFiled(request.getParameter("name")))
				&& (ValidateData
						.checkNameFiled(request.getParameter("surname")))
				&& (ValidateData.checkNumber(request.getParameter("account")))
				&& (ValidateData.checkNumber(request.getParameter("balance")))) {

			login = request.getParameter("login");
			password = request.getParameter("password");
			name = request.getParameter("name");
			surname = request.getParameter("surname");
			account = Integer.valueOf(request.getParameter("account"));
			balance = Integer.valueOf(request.getParameter("balance"));
			role = Integer.valueOf(request.getParameter("paymentList"));
			checkFormField = true;
		}
		System.out.println(checkFormField);

		if (checkFormField) {

			if (userService.addUser(login, password, surname, name, role,
					account, balance)) {
				User user = userService.getUser(login, password);
				session.setAttribute("id", String.valueOf(user.getId()));
				session.setAttribute("errorRegistration", null);
				path = Constants.PAGES_MAIN;
				try {
					response.sendRedirect("ServletController?command=user");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				session.setAttribute("errorRegistration",
						"This login has been used");
				try {
					response.sendRedirect("ServletController?command=registration");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}else{
			session.setAttribute("errorRegistration",
					"Incorrect data in form fields");
			try {
				response.sendRedirect("ServletController?command=registration");
				logger.error("Mistake of regestration");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return path;
	}
}

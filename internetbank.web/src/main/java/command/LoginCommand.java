package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import client.ClientType;
import entity.Archive;
import entity.User;
import entity.Account;
import service.AccountService;
import service.ArchiveService;
import service.UserService;
import bundle.Constants;

public class LoginCommand implements Command {
	static Logger logger = Logger.getLogger(LoginCommand.class);
	static Logger loggerInfo = Logger.getLogger(LoginCommand.class);
	
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = Constants.PAGE_INDEX;
		HttpSession session = request.getSession();
		
		loggerInfo.setLevel(Level.INFO);
		logger.setLevel(Level.ERROR);

		UserService usersService = new UserService();
		AccountService accountService = new AccountService();
		ArchiveService archiveService = new ArchiveService();

		User user = null;

		user = usersService.getUser(
				request.getParameter(Constants.PARAM_EMAIL),
				request.getParameter(Constants.PARAM_PASSWORD));

		if (user == null) {
			request.setAttribute("error", "Wrong email or password");
			/*loggerInfo.info("Mistake of loggin "
					+ request.getParameter(Constants.PARAM_EMAIL) + " "
					+ request.getParameter(Constants.PARAM_PASSWORD));*/
			logger.error("Mistake of loggin "
					+ request.getParameter(Constants.PARAM_EMAIL) + " "
					+ request.getParameter(Constants.PARAM_PASSWORD));

		} else {
			ArrayList<Archive> archive = (ArrayList<Archive>) archiveService
					.getArchiveByUserID(user.getId());
			Account account = accountService.getAccontsByUserID(user.getId());
			session.setAttribute("id", String.valueOf(user.getId()));
			session.setAttribute("userType", ClientType.USER);
			request.setAttribute("username", user.getName());
			request.setAttribute("surname", user.getSurname());
			request.setAttribute("accounts", account);
			request.setAttribute("archive", archive);
			page = Constants.PAGES_MAIN;
		}
		return page;
	}

}

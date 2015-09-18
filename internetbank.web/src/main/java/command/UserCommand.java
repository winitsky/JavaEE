package command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;
import entity.Archive;
import entity.User;
import service.AccountService;
import service.ArchiveService;
import service.UserService;
import bundle.Constants;

public class UserCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String page = Constants.PAGE_INDEX;
		HttpSession session = request.getSession();

		if (session.getAttribute("id") != null) {
			UserService userService = new UserService();
			AccountService accountService = new AccountService();
			ArchiveService archiveService = new ArchiveService();

			String userID = (String) session.getAttribute("id");
			User user = userService.getUserByID(Integer.valueOf(userID));
			List<Archive> archive =  archiveService.getArchiveByUserID(user.getId());
			Account account = accountService.getAccontsByUserID(user.getId());
			request.setAttribute("username", user.getName());
			request.setAttribute("surname", user.getSurname());
			request.setAttribute("accounts", account);
			request.setAttribute("archive", archive);

			page = Constants.PAGES_MAIN;
		}
		return page;
	}

}

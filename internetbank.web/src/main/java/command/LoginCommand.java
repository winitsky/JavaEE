package command;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Archive;
import entity.User;
import entity.Account;
import service.AccountService;
import service.ArchiveService;
import service.UserService;
import bundle.Constants;

public class LoginCommand implements Command {
	
	
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = Constants.PAGE_INDEX;
        HttpSession session = request.getSession();
        UserService usersService = new UserService();
        AccountService accountService= new AccountService();
        ArchiveService archiveService=new  ArchiveService();
        User user = null;

        user = usersService.getUser(request.getParameter(Constants.PARAM_EMAIL), request.getParameter(Constants.PARAM_PASSWORD));
        ArrayList<Account> accounts= (ArrayList<Account>) accountService.getAccontsByUserID(user.getId());
        
        ArrayList<Archive> archive=(ArrayList<Archive>) archiveService.getArchiveByUserID(user.getId());
        if (user.getLogin() == null) {
            request.setAttribute("error", "Wrong email or password");

        } else {
            session.setAttribute("id", String.valueOf(user.getId()));
            request.setAttribute("username", user.getName());
            request.setAttribute("surname", user.getSurname());
            request.setAttribute("accounts", accounts);
            request.setAttribute("archive", archive);
            page = Constants.PAGES_MAIN;
        }
        return page;
    }

}

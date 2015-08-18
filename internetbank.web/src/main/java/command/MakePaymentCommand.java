package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;
import entity.User;
import service.AccountService;
import service.ArchiveService;
import service.UserService;
import bundle.Constants;

public class MakePaymentCommand implements Command {

	private boolean checkString(String string) {
		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String path = Constants.PAGES_PAYMENT;

		ArchiveService archiveService = new ArchiveService();
		UserService userService = new UserService();
		AccountService accountService = new AccountService();

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");
		User user = userService.getUserByID(Integer.valueOf(userID));
		
		ArrayList<Account> accounts = (ArrayList<Account>) accountService
				.getAccontsByUserID(user.getId());

		if (checkString(request.getParameter("sum"))) {
			int sum = Integer.valueOf(request.getParameter("sum"));
			int operationID = Integer.valueOf(request.getParameter("paymentList"));
			if (accounts.get(0).getBalance() > sum) {
				archiveService.addRecord(Integer.valueOf(userID), operationID,
						sum, "20150814");
				accountService.updateBalance(sum, accounts.get(0));
				path = Constants.PAGES_MAIN;
				session.setAttribute("errorpayment",null);
				try {
					response.sendRedirect("ServletController?command=user");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				session.setAttribute("errorpayment", "Is not enough money on your account");
				try {
					response.sendRedirect("ServletController?command=payment");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			session.setAttribute("errorpayment", "Incorrect input sum, or you did not choose payment");
			try {
				response.sendRedirect("ServletController?command=payment");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return path;
	}

}

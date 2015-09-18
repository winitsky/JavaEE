package command;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Account;
import entity.User;
import service.AccountService;
import service.ArchiveService;
import service.UserService;
import validate.ValidateData;
import bundle.Constants;

public class MakePaymentCommand implements Command {


	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		String path = Constants.PAGES_PAYMENT;
		UserService userService = new UserService();

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");
		User user = userService.getUserByID(Integer.valueOf(userID));

		if (ValidateData.checkNumberField(request.getParameter("sum")) && (!request.getParameter("sum").equals("0"))) {
			int sum = Integer.valueOf(request.getParameter("sum"));
			AccountService accountService = new AccountService();
			Account account = accountService.getAccontsByUserID(user.getId());
			if (accountService.updateBalance(sum, account)) {
				int operationID = Integer.valueOf(request
						.getParameter("paymentList"));
				ArchiveService archiveService = new ArchiveService();
				archiveService.addRecord(Integer.valueOf(userID), operationID,
						sum, "20150908");
				path = Constants.PAGES_MAIN;
				session.setAttribute("errorpayment", null);
				try {
					response.sendRedirect("ServletController?command=user");
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				session.setAttribute("errorpayment",
						"Is not enough money on your account");
				try {
					response.sendRedirect("ServletController?command=payment");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			session.setAttribute("errorpayment",
					"Incorrect input sum, or you did not choose payment");
			try {
				response.sendRedirect("ServletController?command=payment");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return path;
	}

}

package command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Operation;
import service.OperationService;
import bundle.Constants;

public class PaymentCommand implements Command {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("id");
		OperationService operationService = new OperationService();
		ArrayList<Operation> operations = (ArrayList<Operation>) operationService
				.getOperationsByUserID(Integer.valueOf(userID));
		request.setAttribute("operations", operations);
		
		return Constants.PAGES_PAYMENT;
	}

}

package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import command.Command;
import command.CommandFactory;

public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String log4j = config.getInitParameter("log4j-pass");
		String path = getServletContext().getRealPath(log4j);
		PropertyConfigurator.configure(path);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		performAction(request, response);

	}

	private void performAction(HttpServletRequest request,
			HttpServletResponse response) {
	
		String paramCommand = request.getParameter("command");
		if (paramCommand == null) {
			throw new IllegalArgumentException("Param command is null");
		}
		Command command = CommandFactory.getCommand(paramCommand);
		String nextPage = command.execute(request, response);
		if (!response.isCommitted()) {
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher(nextPage);
			try {
				requestDispatcher.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

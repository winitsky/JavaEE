package org.internetbank.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.internetbank.service.ServiceAccount;
import org.internetbank.service.ServiceArchive;
import org.internetbank.service.ServiceOperation;
import org.internetbank.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import entity.Account;
import entity.Archive;
import entity.Operation;
import entity.User;

@Controller
@SessionAttributes("id")
@RequestMapping("/")
public class AppController {

	@Autowired
	ServiceUser serviceUser;

	@Autowired
	ServiceAccount serviceAccount;

	@Autowired
	ServiceArchive serviceArchive;

	@Autowired
	ServiceOperation serviceOperation;

	@RequestMapping(value = { "/userlogin" }, method = RequestMethod.GET)
	public String loginUserPage(Model model) {
		return "userlogin";
	}

	@RequestMapping(value = { "/userpage" }, method = RequestMethod.GET)
	public String userPage(Model model, HttpServletRequest request) {
		String login = request.getRemoteUser();
		User currentUser = serviceUser.checkLogin(login);
		Account account = serviceAccount
				.getAccontsByUserID(currentUser.getId());
		List<Archive> archives = serviceArchive.getArchiveByUserID(currentUser
				.getId());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("account", account);
		model.addAttribute("archives", archives);
		model.addAttribute("id", currentUser.getId());

		return "main";
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
     //   model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String indexPage(Model model) {
		return "welcome";
	}

	@RequestMapping(value = { "/welcome" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		return "welcome";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
		User user = new User();
		model.addAttribute(user);
		return "login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String userPage(User user, Model model) {
		String path = "login";

		if (serviceUser.getUser(user.getLogin(), user.getPassword()) != null) {
			User currentUser = serviceUser.getUser(user.getLogin(),
					user.getPassword());
			Account account = serviceAccount.getAccontsByUserID(currentUser
					.getId());
			List<Archive> archives = serviceArchive
					.getArchiveByUserID(currentUser.getId());
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("account", account);
			model.addAttribute("archives", archives);
			model.addAttribute("id", currentUser.getId());
			path = "main";
		}
		return path;
	}

	@RequestMapping(value = { "/main" }, method = RequestMethod.GET)
	public String userPage(User currentUser, Account account, Archive archives,
			Model model) {
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("account", account);
		model.addAttribute("archives", archives);
		return "main";
	}

	@RequestMapping(value = { "/new","/payment" }, method = RequestMethod.GET)
	public String paymentPage(Model model, HttpSession httpSession) {
		Integer userId = (Integer) httpSession.getAttribute("id");
		List<Operation> operations = serviceOperation
				.getOperationsByUserID(userId);
		Archive archiveNew = new Archive();
		User currentUser = serviceUser.getUserByID(userId);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("archiveNew", archiveNew);
		model.addAttribute("operations", operations);
		return "payment";
	}

	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String paymentSave(Model model, HttpSession httpSession,
			@Valid Archive archiveNew,BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/new";
		}
		Integer userId = (Integer) httpSession.getAttribute("id");
		if (serviceAccount.updateBalance(Integer.valueOf(archiveNew.getSum()),
				serviceAccount.getAccontsByUserID(userId))) {
			serviceArchive.addRecord(userId, archiveNew.getOperationID(),
					archiveNew.getSum(), "20150908");
			return "redirect:/savePayment";
		}else{
			model.addAttribute("errorpayment", "Not enought money on your account");
			return "redirect:/new";
		}

	}

	@RequestMapping(value = { "/savePayment" }, method = RequestMethod.GET)
	public String returnUserPage(Model model, HttpSession httpSession) {
		Integer userId = (Integer) httpSession.getAttribute("id");
		User currentUser = serviceUser.getUserByID(userId);
		Account account = serviceAccount.getAccontsByUserID(userId);
		List<Archive> archives = serviceArchive.getArchiveByUserID(userId);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("account", account);
		model.addAttribute("archives", archives);
		return "main";
	}

	@RequestMapping(value = { "/regestration" }, method = RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "regestration";
	}

	@RequestMapping(value = { "/regestration" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, Model model) {
		String path = "regestration";
		if (result.hasErrors()) {
			path = "regestration";
		} else {
			if (serviceUser.addUser(user.getLogin(), user.getPassword(),
					user.getSurname(), user.getName(),
					Integer.valueOf(user.getRole()),
					Integer.valueOf(user.getAccount()),
					Integer.valueOf(user.getBalance()))) {
				User currentUser = serviceUser.getUser(user.getLogin(),
						user.getPassword());
				Account account = serviceAccount.getAccontsByUserID(currentUser
						.getId());
				List<Archive> archives = serviceArchive
						.getArchiveByUserID(currentUser.getId());

				model.addAttribute("currentUser", currentUser);
				model.addAttribute("account", account);
				model.addAttribute("archives", archives);
				model.addAttribute("id", currentUser.getId());
				path = "main";
			} else {
				model.addAttribute("errorRegistration",
						"User with this login exists");
				path = "regestration";
			}
		}
		return path;
	}

}
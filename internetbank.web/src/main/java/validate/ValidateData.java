package validate;

import java.util.regex.Pattern;

public class ValidateData {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String NUMBER_PATTERN = "^[1-9]{1}\\d*$";
	
	private static final String NAME_SURNAME_PATTERN = "[A-ZÀ-ßa-zà-ÿ0-9]{1,30}";//"^[à-ÿÀ-ß¸¨a-zA-Z0-9]+$";
	
	private static final String PASSWORD_PATTERN = "^[à-ÿÀ-ß¸¨a-zA-Z0-9]+$";
	
	
	public static boolean checkLogin(String login){
		System.out.println(login + Pattern.compile(EMAIL_PATTERN).matcher(login).find());
		return Pattern.compile(EMAIL_PATTERN).matcher(login).find();
	}

	public static boolean checkPassword(String password){
		System.out.println(password + Pattern.compile(PASSWORD_PATTERN).matcher(password).find());
		return Pattern.compile(PASSWORD_PATTERN).matcher(password).find();
	}
	
	public static boolean checkNameFiled(String name){
		System.out.println(name + Pattern.compile(NAME_SURNAME_PATTERN).matcher(name).find());
		return Pattern.compile(NAME_SURNAME_PATTERN).matcher(name).find();
	}
	
	public static boolean checkNumber(String number){
		System.out.println(number + Pattern.compile(NUMBER_PATTERN).matcher(number).find());
		return Pattern.compile(NUMBER_PATTERN).matcher(number).find();
	}
	
	
	public static boolean checkNumberField(String string) {

		try {
			Integer.parseInt(string);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean checkEmpty(String string) {
		return (string == null || string.equals(""));
	}
}

package entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class User implements Serializable {
	private int id;
	@Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message="Useraname look has form like email ")
	private String login;
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$" , message="Wrong password, must be with no spaces")
	private String password;
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$" , message="Wrong password, must be with no spaces")
	private String surname;
	@Pattern(regexp="^[а-яА-ЯёЁa-zA-Z0-9]+$" , message="Wrong name, must be with no spaces")
	private String name;
	@Pattern(regexp="^[1-9]{1}\\d*$", message="Role must have number")
	private String role;
	@Pattern(regexp="^[1-9]{1}\\d*$", message="Account must have number")
	private String account;
	@Pattern(regexp="^[1-9]{1}\\d*$", message="Balance must have number")
	private String balance;
	
	private static final long serialVersionUID = 1L;
	
	public User() {
		super();
	}

	public User(String login, String password, String surname, String name) {
		super();
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}
	
	public User(int id, String login, String password, String surname,
			String name) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.name = name;
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balnce) {
		this.balance = balnce;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password="
				+ password + ", surname=" + surname + ", name=" + name
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	
	
	
}

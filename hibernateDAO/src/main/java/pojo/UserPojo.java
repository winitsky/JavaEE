package pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = "Users.getAll", query = "SELECT u from UserPojo u"),
		@NamedQuery(name = "CheckLogin", query = "SELECT u FROM UserPojo u WHERE u.login =:userLogin"), 
		@NamedQuery(name = "GetUserByLoginPassword", query = "SELECT u FROM UserPojo u WHERE u.login =:userLogin AND u.password =:userPassword") })
public class UserPojo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "login", length = 32)
	private String login;

	@Column(name = "password", length = 32)
	private String password;

	@Column(name = "surname", length = 32)
	private String surname;

	@Column(name = "name", length = 32)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<UserRolePojo> roles;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.ALL })
	private Set<ArchivePojo> archive;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = { CascadeType.ALL })
/*	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinTable(name = "accounts", joinColumns = { @JoinColumn(name = "user_id") })*/
	private AccountPojo account;

	public UserPojo() {
		super();

	}

	public UserPojo(Integer id, String login, String password, String surname,
			String name) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}

	public UserPojo(String login, String password, String surname, String name) {
		super();
		this.login = login;
		this.password = password;
		this.surname = surname;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<UserRolePojo> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRolePojo> roles) {
		this.roles = roles;
	}

	public Set<ArchivePojo> getArchive() {
		return archive;
	}

	public void setArchive(Set<ArchivePojo> archive) {
		this.archive = archive;
	}

	public AccountPojo getAccount() {
		return account;
	}

	public void setAccount(AccountPojo account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserPojo other = (UserPojo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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

	@Override
	public String toString() {
		return "UserPojo [id=" + id + ", login=" + login + ", password="
				+ password + ", surname=" + surname + ", name=" + name + "]";
	}

}

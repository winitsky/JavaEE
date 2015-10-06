package org.internetbank.springdao.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
@NamedQueries({
@NamedQuery(name = "Accounts.getAll", query = "SELECT e from AccountPojo e"),
@NamedQuery(name = "GetAccountByUserID", query = "SELECT e FROM AccountPojo e WHERE e.userId =:userId")})
public class AccountPojo implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "account")
	private Integer account;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "balance")
	private Long balance;
	
	@OneToOne//(mappedBy = "account")
	@JoinColumn(name="user_id", insertable=false, updatable=false,  nullable=false )
	
	private UserPojo user;
	
	
	
	public AccountPojo() {
		super();
		
	}

	public AccountPojo(Integer id, Integer account, Integer userId, Long balance) {
		super();
		this.id = id;
		this.account = account;
		this.userId = userId;
		this.balance = balance;
	}


	public AccountPojo(Integer account, Integer userId, Long balance) {
		super();
		this.account = account;
		this.userId = userId;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getAccount() {
		return account;
	}


	public void setAccount(Integer account) {
		this.account = account;
	}


	
	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	
	public Long getBalance() {
		return balance;
	}


	public void setBalance(Long balance) {
		this.balance = balance;
	}

	

	public UserPojo getUser() {
		return user;
	}

	public void setUser(UserPojo user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		AccountPojo other = (AccountPojo) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "AccountPojo [id=" + id + ", account=" + account + ", userId="
				+ userId + ", balance=" + balance + "\n"+"]";
	}

}

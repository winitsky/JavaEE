package entity;

public class Account {
	private Integer id;
	private int account;
	private int userID;
	private int balance;
	
	public Account() {
		super();
	}

	public Account(int account, int userID, int balance) {
		super();
		this.account = account;
		this.userID = userID;
		this.balance = balance;
	}
	
			
	public Account(Integer id, int account, int userID, int balance) {
		super();
		this.id = id;
		this.account = account;
		this.userID = userID;
		this.balance = balance;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [account=" + account + ", userID=" + userID
				+ ", balance=" + balance + "]";
	}
	
	
	
	
	
}

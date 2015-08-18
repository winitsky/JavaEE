package entity;

public class Account {
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

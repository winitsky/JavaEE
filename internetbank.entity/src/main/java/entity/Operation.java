package entity;

public class Operation {
	private int id;
	private String name;
	private int account;
	private int type;
	
	public Operation() {
		super();
	}

	public Operation(String name, int account, int type) {
		super();
		this.name = name;
		this.account = account;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Operation [id=" + id + ", name=" + name + ", account="
				+ account + ", type=" + type + "]";
	}
	
	

}

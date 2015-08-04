package entity;

public class Archive {
	private int id;
	private int userID;
	private int operationID;
	private int sum;
	private String date;
	private String nameOperaion;
	
	public Archive() {
		super();
	}

	public Archive(int userID, int operationID, int sum, String date) {
		super();
		this.userID = userID;
		this.operationID = operationID;
		this.sum = sum;
		this.date = date;
	}
	
	

	public Archive(int userID, int operationID, int sum, String date,
			String nameOperaion) {
		super();
		this.userID = userID;
		this.operationID = operationID;
		this.sum = sum;
		this.date = date;
		this.nameOperaion = nameOperaion;
	}
	
	

	public String getNameOperaion() {
		return nameOperaion;
	}

	public void setNameOperaion(String nameOperaion) {
		this.nameOperaion = nameOperaion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getOperationID() {
		return operationID;
	}

	public void setOperationID(int operationID) {
		this.operationID = operationID;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Archive [id=" + id + ", userID=" + userID + ", operationID="
				+ operationID + ", sum=" + sum + ", date=" + date
				+ ", nameOperaion=" + nameOperaion + "]";
	}

}

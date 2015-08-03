package entity;

public class Role {
	private int id;
	private int userID;
	private int role;

	public Role() {
		super();
	}

	public Role(int user_id, int role) {
		this.userID = user_id;
		this.role = role;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", userID=" + userID + ", role=" + role + "]";
	}
	
	

}

package VO;

public class User {
	public static final String TABLE_NAME="User"; 
	private String UserID;
	private String userName;
	private String UserPW;
	private int UserType;
	public int getUserType() {
		return UserType;
	}
	public void setUserType(int userType) {
		UserType = userType;
	}
	public User(String userID, String userName, String userPW,int userType) {
		super();
		UserID = userID;
		this.userName = userName;
		UserPW = userPW;
		this.UserType = userType;
	}
	public User(String userName, String userPW,int userType) {
		super();
		this.userName = userName;
		UserPW = userPW;
		this.UserType = userType;
	}
	public User() {
		super();
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPW() {
		return UserPW;
	}
	public void setUserPW(String userPW) {
		UserPW = userPW;
	}
	
}

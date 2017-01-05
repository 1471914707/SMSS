package VO;

public class SharedVariables {
	private String userID;
	private String accountString;
	private int userType;

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getAccountString() {
		return accountString;
	}

	public void setAccountString(String accountString) {
		this.accountString = accountString;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
		
	}
	
}

package VO;

import java.util.Date;

public class Dealing {
	public static final String TABLE_NAME="Dealing"; 
	private String DealingID;
	private double DealingPrice;
	private Date DealingDate;
	private String MemberID;
	private String UserID;
	
	public Dealing(double dealingPrice, Date dealingDate, String memberID,
			String userID) {
		super();
		DealingPrice = dealingPrice;
		DealingDate = dealingDate;
		MemberID = memberID;
		UserID = userID;
	}
	

	public Dealing(double dealingPrice, Date dealingDate, String userID) {
		super();
		DealingPrice = dealingPrice;
		DealingDate = dealingDate;
		UserID = userID;
	}


	public Dealing(String dealingID, double dealingPrice, Date dealingDate,
			String memberID, String userID) {
		super();
		DealingID = dealingID;
		DealingPrice = dealingPrice;
		DealingDate = dealingDate;
		MemberID = memberID;
		UserID = userID;
	}
	
	public Dealing(String dealingID, double dealingPrice, Date dealingDate,
			String userID) {
		super();
		DealingID = dealingID;
		DealingPrice = dealingPrice;
		DealingDate = dealingDate;
		UserID = userID;
	}

	public Dealing() {
		super();
	}

	public String getDealingID() {
		return DealingID;
	}
	public void setDealingID(String dealingID) {
		DealingID = dealingID;
	}
	public double getDealingPrice() {
		return DealingPrice;
	}
	public void setDealingPrice(double dealingPrice) {
		DealingPrice = dealingPrice;
	}
	public Date getDealingDate() {
		return DealingDate;
	}
	public void setDealingDate(Date dealingDate) {
		DealingDate = dealingDate;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	
}

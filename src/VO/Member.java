package VO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {
	public static final String TABLE_NAME="Member"; 
	private String MemberID;
	private String MemberCard;
	private double TotalCost;
	private Date RegDate;
	public Member(String member, String memberCard, double totalCost,
			Date regDate) {
		super();
		MemberID = member;
		MemberCard = memberCard;
		TotalCost = totalCost;
		RegDate = regDate;
	}
	public Member() {
		super();
	}
	public Member(String memberCard, double totalCost, Date regDate) {
		super();
		MemberCard = memberCard;
		TotalCost = totalCost;
		RegDate = regDate;
	}
	public String getMemberID() {
		return MemberID;
	}
	public void setMemberID(String memberID) {
		MemberID = memberID;
	}
	public String getMemberCard() {
		return MemberCard;
	}
	public void setMemberCard(String memberCard) {
		MemberCard = memberCard;
	}
	public double getTotalCost() {
		return TotalCost;
	}
	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}
	public String getRegDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(RegDate);
	}
	public void setRegDate(Date regDate) {
		RegDate = regDate;
	}
	
	
	
}

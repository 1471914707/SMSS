package VO;

public class MerchInfo {
	public static final String TABLE_NAME="MerchInfo"; 
	private String MerchID;
	private String MerchName;
	private double MerchPrice;
	private int MerchNum;
	private String BarCode;
	private String ProvideID;

	public MerchInfo(String merchID, String merchName, double merchPrice,
			int merchNum, String barCode, String provideID) {
		super();
		MerchID = merchID;
		MerchName = merchName;
		MerchPrice = merchPrice;
		MerchNum = merchNum;
		BarCode = barCode;
		ProvideID = provideID;
	}

	public MerchInfo() {
		super();
	}

	public MerchInfo(String merchName, double merchPrice, int merchNum,
			String barCode, String provideID) {
		super();
		MerchName = merchName;
		MerchPrice = merchPrice;
		MerchNum = merchNum;
		BarCode = barCode;
		ProvideID = provideID;
	}

	public String getMerchID() {
		return MerchID;
	}

	public void setMerchID(String merchID) {
		MerchID = merchID;
	}

	public String getMerchName() {
		return MerchName;
	}

	public void setMerchName(String merchName) {
		MerchName = merchName;
	}

	public double getMerchPrice() {
		return MerchPrice;
	}

	public void setMerchPrice(double merchPrice) {
		MerchPrice = merchPrice;
	}

	public int getMerchNum() {
		return MerchNum;
	}

	public void setMerchNum(int merchNum) {
		MerchNum = merchNum;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public String getProvideID() {
		return ProvideID;
	}

	public void setProvideID(String provideID) {
		ProvideID = provideID;
	}
	
}

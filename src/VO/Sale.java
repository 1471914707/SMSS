package VO;

import java.util.Date;

public class Sale {
	public static final String TABLE_NAME="Sale"; 
	private String SaleID;
	private String BarCode;
	private Date SaleDate;
	private int SaleNum;
	private double SalePrice;
	public Sale() {
		super();
	}
	
	public Sale(String barCode, Date saleDate, int saleNum, double salePrice) {
		super();
		BarCode = barCode;
		SaleDate = saleDate;
		SaleNum = saleNum;
		SalePrice = salePrice;
	}

	public Sale(String saleID, String barCode, Date saleDate, int saleNum,
			double salePrice) {
		super();
		SaleID = saleID;
		BarCode = barCode;
		SaleDate = saleDate;
		SaleNum = saleNum;
		SalePrice = salePrice;
	}
	public String getSaleID() {
		return SaleID;
	}
	public void setSaleID(String saleID) {
		SaleID = saleID;
	}

	public String getBarCode() {
		return BarCode;
	}
	public void setBarCode(String barCode) {
		BarCode = barCode;
	}
	public Date getSaleDate() {
		return SaleDate;
	}
	public void setSaleDate(Date saleDate) {
		SaleDate = saleDate;
	}
	public int getSaleNum() {
		return SaleNum;
	}
	public void setSaleNum(int saleNum) {
		SaleNum = saleNum;
	}
	public double getSalePrice() {
		return SalePrice;
	}
	public void setSalePrice(double salePrice) {
		SalePrice = salePrice;
	}
	
}

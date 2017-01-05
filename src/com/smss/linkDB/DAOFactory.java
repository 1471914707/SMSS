package com.smss.linkDB;

import DAOImpl.CountInfoDAOImpl;
import DAOImpl.DealingDAOImpl;
import DAOImpl.MemberDAOImpl;
import DAOImpl.MerchInfoDAOImpl;
import DAOImpl.ProvideDAOImpl;
import DAOImpl.SaleDAOImpl;
import DAOImpl.UserDAOImpl;

public class DAOFactory {
	public static ProvideDAOImpl getProvideInstance(){
		return new ProvideDAOImpl();
	}
	
	public static DealingDAOImpl getDealingInstance(){
		return new DealingDAOImpl();
	}
	
	public static MemberDAOImpl getMemberInstance(){
		return new MemberDAOImpl();
	}
	
	public static MerchInfoDAOImpl getMerchInfoInstance(){
		return new MerchInfoDAOImpl();
	}
	
	public static SaleDAOImpl getSaleInstance(){
		return new SaleDAOImpl();
	}
	
	public static UserDAOImpl getUserInstance() {
		return new UserDAOImpl();
	}
	
	public static CountInfoDAOImpl getCountInfostance(){
		return new CountInfoDAOImpl(); 
	}
}


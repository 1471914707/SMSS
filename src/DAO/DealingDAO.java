package DAO;

import java.util.List;

import VO.Dealing;

public interface DealingDAO {
	/*
	 * 添加交易
	 * @param Dealing,Dealing类型
	 */
	public void save(Dealing Dealing,int num);
	
	/*
	 * 删除交易
	 * @param Dealing
	 */
	public void delete(Dealing Dealing);
	
	/*
	 * 更新交易
	 * @param Dealing
	 */
	public void update(Dealing Dealing);
	
	/*
	 * 根据ID查询交易
	 * @param DealingID
	 */
	public Dealing searchByID(String DealingID);
	
	/*
	 * 根据指定属性查询
	 * @param property
	 */
	public List searchByLikeProperty(String property,String val);
	
	/*
	 * 按分页获取
	 */
	public List searchByPage(int index,int num);
}

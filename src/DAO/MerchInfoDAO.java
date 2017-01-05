package DAO;

import java.util.List;

import VO.MerchInfo;

public interface MerchInfoDAO {
	/*
	 * 添加货物
	 * @param MerchInfo,MerchInfo类型
	 */
	public void save(MerchInfo merchInfo);
	
	/*
	 * 删除货物
	 * @param MerchInfo
	 */
	public void delete(MerchInfo merchInfo);
	
	/*
	 * 更新货物
	 * @param MerchInfo
	 */
	public void update(MerchInfo merchInfo);
	
	/*
	 * 根据ID查询货物
	 * @param MerchInfoID
	 */
	public MerchInfo searchByBarCode(String barcode);
	
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

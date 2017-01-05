package DAO;

import java.util.List;

import VO.Sale;

public interface SaleDAO {
	/*
	 * 添加销售
	 * @param Sale,Sale类型
	 */
	public void save(Sale sale);
	
	/*
	 * 删除销售
	 * @param Sale
	 */
	public void delete(Sale sale);
	
	/*
	 * 更新销售
	 * @param Sale
	 */
	public void update(Sale sale);
	
	/*
	 * 根据ID查询销售
	 * @param SaleID
	 */
	public Sale searchByID(String saleID);
	
	/*
	 * 根据指定属性查询
	 * @param property
	 */
	public List searchByLikeProperty(String property,String val);
	
	/*
	 * 根据二维码查询
	 * @param property
	 */
	public List searchByBarCode(String barcode);
	
	/*
	 * 按分页获取
	 */
	public List searchByPage(int index,int num);
}

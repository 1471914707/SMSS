package DAO;

import java.util.List;

import VO.Provide;

public interface ProvideDAO {
	/*
	 * 添加供应商
	 * @param Provide,Provide类型
	 */
	public void save(Provide provide);
	
	/*
	 * 删除供应商
	 * @param Provide
	 */
	public void delete(Provide provide);
	
	/*
	 * 更新供应商
	 * @param Provide
	 */
	public void update(Provide provide);
	
	/*
	 * 根据ID查询供应商
	 * @param ProvideID
	 */
	public Provide searchByID(String ProvideID);
	
	/*
	 * 根据指定属性查询
	 * @param property
	 */
	public List searchByLikeProperty(String property,String val);
	
	/*
	 * 按分页获取
	 */
	public List searchByPage(int index,int num);
	/*
	 * 获取最后一条数据
	 */
	public Provide selectLast();
}

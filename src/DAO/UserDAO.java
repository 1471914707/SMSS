package DAO;

import java.util.List;

import VO.User;

public interface UserDAO {
	/*
	 * 添加用户
	 * @param User,User类型
	 */
	public void save(User user);
	
	/*
	 * 删除用户
	 * @param User
	 */
	public void delete(User user);
	
	/*
	 * 更新用户
	 * @param User
	 */
	public void update(User user);
	
	/*
	 * 根据ID查询用户
	 * @param UserID
	 */
	public User searchByID(String userID);
	
	
	/*
	 * 根据ID查询用户
	 * @param UserName
	 */
	public User searchByName(String userName);
	
	/*
	 * 根据指定属性查询
	 * @param property
	 */
	public List searchByLikeProperty(String property,String val);
	
	/*
	 * 按分页获取
	 */
	public List searchByPage(int index,int num);
	
	public User selectLast();
}

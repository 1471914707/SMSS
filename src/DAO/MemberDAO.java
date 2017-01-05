package DAO;

import java.util.List;

import VO.Member;

public interface MemberDAO {
	/*
	 * 添加会员
	 * @param Member,Member类型
	 */
	public void save(Member member);
	
	/*
	 * 删除会员
	 * @param Member
	 */
	public void delete(Member member);
	
	/*
	 * 更新会员
	 * @param Member
	 */
	public void update(Member member);
	
	/*
	 * 根据ID查询会员
	 * @param MemberID
	 */
	public Member searchByID(String memberID);
	
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
	 * 按卡号增加消费金额
	 */
	public void updateTotalCost(String card,double cost);
	
	public Member selectLast();
	 
}

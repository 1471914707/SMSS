package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smss.linkDB.DbcpConnectionPool;

import VO.Dealing;
import VO.Member;
import DAO.MemberDAO;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public void save(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
			try {
				conn = DbcpConnectionPool.getConnection();
				CallableStatement cstmt = conn.prepareCall("call Member_save(?,?,?)");
				cstmt.setString(1, member.getMemberCard());
				cstmt.setDouble(2, member.getTotalCost());
				cstmt.setString(3, member.getRegDate());
				cstmt.execute();
				cstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_delete(?)}");
			cstmt.setString(1, member.getMemberID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_update(?,?,?,?)}");
			cstmt.setString(1,member.getMemberID());
			cstmt.setString(2, member.getMemberCard());
			cstmt.setDouble(3, member.getTotalCost());
			cstmt.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(member.getRegDate()));
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Member searchByID(String memberID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Member member = new Member();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_searchByID(?)}");
			cstmt.setString(1, memberID);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				member.setMemberID(rs.getString("MemberID"));
				member.setMemberCard(rs.getString("MemberCard"));
				member.setTotalCost(rs.getDouble("TotalCost"));
				String time = rs.getString("RegDate");
				try {
					member.setRegDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (member.getMemberID()!= null){
			return member;
		}else{
			return null;
		}
	}

	@Override
	public List searchByLikeProperty(String property, String val) {
		// TODO Auto-generated method stub
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = DbcpConnectionPool.getConnection();
			String sql = "select * from "+Member.TABLE_NAME+" where "+property+" like '%"+val+"%'";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				Member member = new Member();
				member.setMemberID(rs.getString("MemberID"));
				member.setMemberCard(rs.getString("MemberCard"));
				member.setTotalCost(rs.getDouble("TotalCost"));
				String time = rs.getString("DealingDate");
				try {
					member.setRegDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(member);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List searchByPage(int index, int num) {
		// TODO Auto-generated method stub
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				Member member = new Member();
				member.setMemberID(rs.getString("MemberID"));
				member.setMemberCard(rs.getString("MemberCard"));
				member.setTotalCost(rs.getDouble("TotalCost"));
				String time = rs.getString("RegDate");
				try {
					member.setRegDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(member);
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateTotalCost(String card, double cost) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_updateTotalCost(?,?)}");
			cstmt.setString(1, card);
			cstmt.setDouble(2, cost);
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Member selectLast() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Member member = new Member();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Member_selectLast()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				member.setMemberID(rs.getString("MemberID"));
				member.setMemberCard(rs.getString("MemberCard"));
				member.setTotalCost(rs.getDouble("TotalCost"));
				String time = rs.getString("RegDate");
				try {
					member.setRegDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return member;
	}

}

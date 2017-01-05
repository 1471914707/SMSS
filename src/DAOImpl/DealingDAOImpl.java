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

import DAO.DealingDAO;
import VO.Dealing;

import com.smss.linkDB.DbcpConnectionPool;

public class DealingDAOImpl implements DealingDAO{

	@Override
	public void save(Dealing dealing,int x) {
		// TODO Auto-generated method stub
		Connection conn = null;
			try {
				conn = DbcpConnectionPool.getConnection();
				CallableStatement cstmt = conn.prepareCall("call Dealing_save(?,?,?,?,?)");
				cstmt.setDouble(1, dealing.getDealingPrice());
				cstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dealing.getDealingDate()));
				cstmt.setString(3,dealing.getMemberID());
				cstmt.setString(4, dealing.getUserID());
				cstmt.setInt(5, x);
				cstmt.execute();
				cstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Dealing dealing) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Dealing_delete(?)}");
			cstmt.setString(1, dealing.getDealingID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Dealing dealing) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Dealing_update(?,?,?,?,?)}");
			cstmt.setString(1,dealing.getDealingID());
			cstmt.setDouble(2, dealing.getDealingPrice());
			cstmt.setString(3, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dealing.getDealingDate()));
			cstmt.setString(4,dealing.getMemberID());
			cstmt.setString(5, dealing.getUserID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Dealing searchByID(String DealingID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Dealing dealing = new Dealing();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Dealing_searchByID(?)}");
			cstmt.setString(1, DealingID);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				dealing.setDealingID(rs.getString("DealingID"));
				dealing.setDealingID(rs.getString("DealingID"));
				String time = rs.getString("DealingDate");
				try {
					dealing.setDealingDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dealing.setUserID(rs.getString("UserID"));
				dealing.setMemberID(rs.getString("MemberID"));
				dealing.setDealingPrice(rs.getFloat("DealingPrice"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (dealing.getDealingID() != null){
			return dealing;
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
			String sql = "select * from "+Dealing.TABLE_NAME+" where "+property+" like '%"+val+"%'";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				Dealing dealing = new Dealing();
				dealing.setDealingID(rs.getString("DealingID"));
				String time = rs.getString("DealingDate");
				try {
					dealing.setDealingDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dealing.setUserID(rs.getString("UserID"));
				dealing.setMemberID(rs.getString("MemberID"));
				dealing.setDealingPrice(rs.getDouble("DealingPrice"));
				list.add(dealing);
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
			CallableStatement cstmt = conn.prepareCall("{call Dealing_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				Dealing dealing = new Dealing();
				dealing.setDealingID(rs.getString("DealingID"));
				String time = rs.getString("DealingDate");
				try {
					dealing.setDealingDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dealing.setUserID(rs.getString("UserID"));
				dealing.setMemberID(rs.getString("MemberID"));
				dealing.setDealingPrice(rs.getDouble("DealingPrice"));
				list.add(dealing);
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

}

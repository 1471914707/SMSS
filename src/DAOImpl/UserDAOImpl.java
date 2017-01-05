package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.smss.linkDB.DbcpConnectionPool;

import VO.Member;
import VO.User;
import DAO.UserDAO;

public class UserDAOImpl implements UserDAO {

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("call User_save(?,?,?)");
			cstmt.setString(1, user.getUserName());
			cstmt.setString(2, user.getUserPW());
			cstmt.setInt(3, user.getUserType());
			cstmt.execute();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call User_delete(?)}");
			cstmt.setString(1, user.getUserID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call User_update(?,?,?,?)}");
			cstmt.setString(1, user.getUserID());
			cstmt.setString(2, user.getUserName());
			cstmt.setString(3, user.getUserPW());
			cstmt.setInt(4, user.getUserType());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User searchByID(String userID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		User user = new User();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call User_searchByID(?)}");
			cstmt.setString(1, userID);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPW(rs.getString("UserPW"));
				user.setUserType(rs.getInt("UserType"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user.getUserID()!= null){
			return user;
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
			String sql = "select * from "+User.TABLE_NAME+" where "+property+" like '%"+val+"%'";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPW(rs.getString("UserPW"));
				user.setUserType(rs.getInt("UserType"));
				list.add(user);
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
			CallableStatement cstmt = conn.prepareCall("{call User_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPW(rs.getString("UserPW"));
				user.setUserType(rs.getInt("UserType"));
				list.add(user);
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
	public User searchByName(String userName) {
		// TODO Auto-generated method stub
		Connection conn = null;
		User user = new User();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call User_searchByName(?)}");
			cstmt.setString(1, userName);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPW(rs.getString("UserPW"));
				user.setUserType(rs.getInt("UserType"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user.getUserID()!= null){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public User selectLast() {
		// TODO Auto-generated method stub
		Connection conn = null;
		User user = new User();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call User_selectLast()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				user.setUserID(rs.getString("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setUserPW(rs.getString("UserPW"));
				user.setUserType(rs.getInt("UserType"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return user;
	}
}

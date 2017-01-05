package DAOImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.ProvideDAO;
import VO.Provide;

import com.smss.linkDB.DbcpConnectionPool;

public class ProvideDAOImpl implements ProvideDAO{

	@Override
	public void save(Provide provide) {
		// TODO Auto-generated method stub
		Connection conn = null;
			try {
				conn = DbcpConnectionPool.getConnection();
				CallableStatement cstmt = conn.prepareCall("call provide_save(?,?,?)");
				cstmt.setString(1, provide.getProvideName());
				cstmt.setString(2, provide.getProvideAddress());
				cstmt.setString(3, provide.getProvidePhone());
				cstmt.execute();
				cstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Provide provide) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call provide_delete(?)}");
			cstmt.setString(1, provide.getProvideID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Provide provide) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call provide_update(?,?,?,?)}");
			cstmt.setString(1, provide.getProvideID());
			cstmt.setString(2, provide.getProvideName());
			cstmt.setString(3, provide.getProvideAddress());
			cstmt.setString(4, provide.getProvidePhone());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Provide searchByID(String ProvideID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Provide provide = new Provide();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call provide_searchByID(?)}");
			cstmt.setString(1, ProvideID);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				provide.setProvideID(rs.getString("ProvideID"));
				provide.setProvideName(rs.getString("ProvideName"));
				provide.setProvidePhone(rs.getString("ProvidePhone"));
				provide.setProvideAddress(rs.getString("ProvideAddress"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (provide.getProvideID() != null){
			return provide;
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
			String sql = "select * from "+Provide.TABLE_NAME+" where "+property+" like '%"+val+"%'";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				Provide provide = new Provide();
				provide.setProvideID(rs.getString("ProvideID"));
				provide.setProvideName(rs.getString("ProvideName"));
				provide.setProvidePhone(rs.getString("ProvidePhone"));
				provide.setProvideAddress(rs.getString("ProvideAddress"));
				list.add(provide);
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
			CallableStatement cstmt = conn.prepareCall("{call provide_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				Provide provide = new Provide();
				provide.setProvideID(rs.getString("ProvideID"));
				provide.setProvideName(rs.getString("ProvideName"));
				provide.setProvidePhone(rs.getString("ProvidePhone"));
				provide.setProvideAddress(rs.getString("ProvideAddress"));
				list.add(provide);
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
	public Provide selectLast() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Provide provide = new Provide();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call provide_selectLast()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				provide.setProvideID(rs.getString("ProvideID"));
				provide.setProvideName(rs.getString("ProvideName"));
				provide.setProvidePhone(rs.getString("ProvidePhone"));
				provide.setProvideAddress(rs.getString("ProvideAddress"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return provide;
	}

}

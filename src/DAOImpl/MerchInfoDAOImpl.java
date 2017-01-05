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

import VO.Dealing;
import VO.MerchInfo;
import DAO.MerchInfoDAO;

public class MerchInfoDAOImpl implements MerchInfoDAO {

	@Override
	public void save(MerchInfo merchInfo) {
		// TODO Auto-generated method stub
		Connection conn = null;
			try {
				conn = DbcpConnectionPool.getConnection();
				CallableStatement cstmt = conn.prepareCall("call MerchInfo_save(?,?,?,?,?)");
				cstmt.setString(1,merchInfo.getMerchName());
				cstmt.setDouble(2, merchInfo.getMerchPrice());
				cstmt.setInt(3, merchInfo.getMerchNum());
				cstmt.setString(4, merchInfo.getBarCode());
				cstmt.setString(5, merchInfo.getProvideID());
				cstmt.execute();
				cstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void delete(MerchInfo merchInfo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call MerchInfo_delete(?)}");
			cstmt.setString(1, merchInfo.getMerchID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(MerchInfo merchInfo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call MerchInfo_update(?,?,?,?,?,?)}");
			cstmt.setString(1, merchInfo.getMerchID());
			cstmt.setString(2,merchInfo.getMerchName());
			cstmt.setDouble(3, merchInfo.getMerchPrice());
			cstmt.setInt(4, merchInfo.getMerchNum());
			cstmt.setString(5, merchInfo.getBarCode());
			cstmt.setString(6, merchInfo.getProvideID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public MerchInfo searchByBarCode(String barcode) {
		// TODO Auto-generated method stub
		Connection conn = null;
		MerchInfo merchInfo = new MerchInfo();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call MerchInfo_searchByBarCode(?)}");
			cstmt.setString(1, barcode);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				merchInfo.setBarCode(rs.getString("barCode"));
				merchInfo.setMerchID(rs.getString("merchID"));
				merchInfo.setMerchName(rs.getString("merchName"));
				merchInfo.setMerchPrice(rs.getDouble("merchPrice"));
				merchInfo.setProvideID(rs.getString("provideID"));
				merchInfo.setMerchNum(rs.getInt("merchNum"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (merchInfo.getMerchID() != null){
			return merchInfo;
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
			String sql = "select * from "+MerchInfo.TABLE_NAME+" where "+property+" like '%"+val+"%'";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				MerchInfo merchInfo = new MerchInfo();
				merchInfo.setBarCode(rs.getString("barCode"));
				merchInfo.setMerchID(rs.getString("merchID"));
				merchInfo.setMerchName(rs.getString("merchName"));
				merchInfo.setMerchPrice(rs.getDouble("merchPrice"));
				merchInfo.setProvideID(rs.getString("provideID"));
				merchInfo.setMerchNum(rs.getInt("merchNum"));
				list.add(merchInfo);
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
			CallableStatement cstmt = conn.prepareCall("{call MerchInfo_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				MerchInfo merchInfo = new MerchInfo();
				merchInfo.setBarCode(rs.getString("barCode"));
				merchInfo.setMerchID(rs.getString("merchID"));
				merchInfo.setMerchName(rs.getString("merchName"));
				merchInfo.setMerchPrice(rs.getDouble("merchPrice"));
				merchInfo.setProvideID(rs.getString("provideID"));
				merchInfo.setMerchNum(rs.getInt("merchNum"));
				list.add(merchInfo);
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

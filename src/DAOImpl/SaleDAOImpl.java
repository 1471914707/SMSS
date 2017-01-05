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

import VO.Member;
import VO.Sale;
import DAO.SaleDAO;

public class SaleDAOImpl implements SaleDAO {

	@Override
	public void save(Sale sale) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("call Sale_save(?,?,?,?)");
			cstmt.setString(1,sale.getBarCode());
			cstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sale.getSaleDate()));
			cstmt.setInt(3, sale.getSaleNum());
			cstmt.setDouble(4, sale.getSalePrice());
			cstmt.execute();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Sale sale) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Sale_delete(?)}");
			cstmt.setString(1, sale.getSaleID());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Sale sale) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Sale_update(?,?,?,?,?)}");
			cstmt.setString(1, sale.getSaleID());
			cstmt.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sale.getSaleDate()));
			cstmt.setInt(3, sale.getSaleNum());
			cstmt.setDouble(4, sale.getSalePrice());
			cstmt.setString(5, sale.getBarCode());
			cstmt.executeUpdate();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Sale searchByID(String saleID) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Sale sale = new Sale();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Sale_searchByID(?)}");
			cstmt.setString(1, saleID);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				sale.setSaleID(rs.getString("saleID"));
				sale.setBarCode(rs.getString("BarCode"));
				String time = rs.getString("SaleDate");
				try {
					sale.setSaleDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sale.setSaleNum(rs.getInt("saleNum"));
				sale.setSalePrice(rs.getDouble("SalePrice"));
			}
			rs.close();
			cstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sale.getSaleID()!= null){
			return sale;
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
			String sql = "select * from "+Sale.TABLE_NAME+" where "+property+" like '%"+val+"%' ORDER BY SaleDate DESC";
			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next()){
				Sale sale = new Sale();
				sale.setSaleID(rs.getString("saleID"));
				sale.setBarCode(rs.getString("BarCode"));
				String time = rs.getString("SaleDate");
				try {
					sale.setSaleDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sale.setSaleNum(rs.getInt("saleNum"));
				sale.setSalePrice(rs.getDouble("SalePrice"));
				list.add(sale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List searchByBarCode(String barcode) {
		// TODO Auto-generated method stub
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Sale_searchByBarCode(?)}");
			cstmt.setString(1, barcode);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				Sale sale = new Sale();
				sale.setSaleID(rs.getString("saleID"));
				sale.setBarCode(rs.getString("BarCode"));
				String time = rs.getString("SaleDate");
				try {
					sale.setSaleDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sale.setSaleNum(rs.getInt("saleNum"));
				sale.setSalePrice(rs.getDouble("SalePrice"));
				list.add(sale);
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
	public List searchByPage(int index, int num) {
		// TODO Auto-generated method stub
		Connection conn = null;
		List list = new ArrayList();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call Sale_searchByPage(?,?)}");
			if (index <0) index= 0;
			if (num < 0) num= 0;
			cstmt.setInt(1, (index-1)*num);
			cstmt.setInt(2, num);
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				Sale sale = new Sale();
				sale.setSaleID(rs.getString("saleID"));
				sale.setBarCode(rs.getString("BarCode"));
				String time = rs.getString("SaleDate");
				try {
					sale.setSaleDate((Date)(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parseObject(time)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sale.setSaleNum(rs.getInt("saleNum"));
				sale.setSalePrice(rs.getDouble("SalePrice"));
				list.add(sale);
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

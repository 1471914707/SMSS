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
import java.util.Vector;

import org.w3c.dom.ls.LSInput;

import VO.CountInfo;

import com.smss.linkDB.DbcpConnectionPool;

import DAO.CountInfoDAO;

public class CountInfoDAOImpl implements CountInfoDAO {

	@Override
	public List getAllCountInfo() {
		// TODO Auto-generated method stub
		Connection conn = null;
		List list  = new ArrayList();
		try {
			conn = DbcpConnectionPool.getConnection();
			CallableStatement cstmt = conn.prepareCall("{call getAllCountInfo()}");
			ResultSet rs = cstmt.executeQuery();
			while(rs.next()){
				CountInfo countInfo = new CountInfo();
				countInfo.setName(rs.getString("CountName"));
				countInfo.setNum(rs.getDouble("CountNum"));
				list.add(countInfo);
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
	public double getCountByTag(String tag) {
		// TODO Auto-generated method stub
		String sql = "select * from count where CountName='"+tag+"'";
		double number = 0;
		Connection connection = null;
		try {
			connection = DbcpConnectionPool.getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
				number = rs.getDouble("CountNum");
			}
			rs.clearWarnings();
			st.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number ;
	}
	
}

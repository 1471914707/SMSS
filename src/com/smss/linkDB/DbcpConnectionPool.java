package com.smss.linkDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DbcpConnectionPool {
    private static BasicDataSource dataSource = null;

    public DbcpConnectionPool() {

    }
    
    @Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
    	if(dataSource!=null && !dataSource.isClosed())
    		dataSource.close();
	}

	/**
     * 鍒濆鍖栨暟鎹簱杩炴帴姹�
     */
    public static void init() 
    {
     	if (dataSource != null)
        {
            try
            {
                dataSource.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            dataSource = null;
        }

        //浣跨敤Properties瀵硅薄瀹氫箟鏁版嵁搴撹繛鎺ユ睜淇℃伅
        try {
            Properties p = new Properties();
            p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
            p.setProperty("url", "jdbc:mysql://localhost:3306/smss?useUnicode=true&characterEncoding=utf-8");
            p.setProperty("password", "1234");
            p.setProperty("username", "root");
            p.setProperty("maxActive", "30");
            p.setProperty("maxIdle", "10");
            p.setProperty("maxWait", "6000");
            p.setProperty("removeAbandoned", "false");
            p.setProperty("removeAbandonedTimeout", "120");
            p.setProperty("testOnBorrow", "true");
            p.setProperty("logAbandoned", "true");       
            //浠ユ寚瀹氫俊鎭垱寤烘暟鎹簮
            dataSource = (BasicDataSource) BasicDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 浠庤繛鎺ユ睜涓幏鍙栬繛鎺�
     * @return
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws  SQLException {
        if (dataSource == null) {
            init();
        }
        Connection conn = null;
        if (dataSource != null) {
            conn = dataSource.getConnection();
        }
        return conn;
    }
}
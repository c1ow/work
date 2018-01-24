package com.work.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class JDBCDao {
	private final static String URL = "jdbc:log4jdbc:oracle:thin:@222.187.115.126:11521:orcl";
	private final static String PWD = "cdms@123";
	private final static String UNAME = "cdms";
	public static Map<String,Object> fileds() {
		Connection connection = null;
		Statement createStatement = null;
		ResultSet executeQuery = null;
		try {
			//获取连接
			Class.forName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
			connection = DriverManager.getConnection(URL,UNAME,PWD);
			//执行sql 语句
			createStatement = connection.createStatement();
			executeQuery = createStatement.executeQuery("select * from MXX_HIS_ASSAY_RESULT_0_500 where id = -1");
			//获取元数据
			ResultSetMetaData metaData = executeQuery.getMetaData();
			//获取列数
			int columnCount = metaData.getColumnCount();
			System.err.println(columnCount);
			for(int i = 1; i < columnCount+1; i ++ ) {
				System.err.println(metaData.getColumnName(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (createStatement !=null ) {
				try {
					createStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (executeQuery !=null) {
				try {
					executeQuery.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public static void main(String[] args) {
		fileds();
	}
}

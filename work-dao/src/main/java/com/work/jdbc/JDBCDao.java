package com.work.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class JDBCDao {
	private final static String URL = "jdbc:mysql://192.168.0.120:3306/testok?useSSL=false";
	private final static String PWD = "tiger";
	private final static String UNAME = "root";
	public static Map<String,Object> fileds() {
		Connection connection = null;
		Statement createStatement = null;
		ResultSet executeQuery = null;
		try {
			//获取连接
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL,UNAME,PWD);
			//执行sql 语句
			createStatement = connection.createStatement();
			executeQuery = createStatement.executeQuery("select table_name from information_schema.tables WHERE TABLE_SCHEMA = \"testok\"");
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
			
		}
		return null;
	}
	//关闭数据连接
	public static void closedConn(Connection connection,Statement statement,ResultSet resultest) {
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (statement !=null ) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (resultest !=null) {
			try {
				resultest.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		fileds();
	}
}

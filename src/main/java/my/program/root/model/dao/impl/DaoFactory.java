package my.program.root.model.dao.impl;

import java.sql.*;

import my.program.root.model.dao.api.*;

public final class DaoFactory {
	
	public DaoFactory() {
	}
	
	public static WordDao getWordDao() {
		return new WordSQLiteDao();
	}
	
	public static CathegoryDao getCathegoryDao() {
		return new CathegorySQLiteDao();
	}
	
	public static Connection createConnectionToSQLiteDB() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:sebd.s3db");
			System.out.println("База подключена.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}
}
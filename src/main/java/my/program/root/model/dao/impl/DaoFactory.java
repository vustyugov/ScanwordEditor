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
	
	public static ScanwordDao getScanwordDao() {
		return new ScanwordExcelFileDao();
	}
	
	public static Connection createConnectionToSQLiteDB() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:d:\\workspace\\develope\\DataBases\\SQLite\\test.s3db");
			System.out.println("База подключена.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}
	
	public static void closeConnectionWithSQLiteDB(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
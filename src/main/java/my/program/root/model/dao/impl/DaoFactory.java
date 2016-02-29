package my.program.root.model.dao.impl;

import java.io.File;
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
			File file = new File(DaoFactory.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			File dir = file.getAbsoluteFile().getParentFile();
			String path = dir.toString();
			StringBuilder buf = new StringBuilder("jdbc:sqlite:");
			buf.append(path);
			buf.append("\\database\\sedb.s3db");
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(buf.toString());
//			conn = DriverManager.getConnection("jdbc:sqlite:sedb.s3db");
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
package my.program.root;

import java.sql.Connection;
import my.program.root.model.*;
import my.program.root.model.dao.api.*;
import my.program.root.model.dao.impl.*;
import my.program.root.model.dictionary.*;

public class ScanwordEditorApp {
	
	public static void main(String[] args) {
		loadDictionaryFromDB(StorageType.SQLite, Dictionary.getInstance());
		ScanwordEditorModel.launch(args);
	}

	private static void loadDictionaryFromDB(StorageType sType, Dictionary dic) {
		switch (sType) {
			case SQLite:
				Connection conn = DaoFactory.createConnectionToSQLiteDB();
				CathegoryDao cathDao = DaoFactory.getCathegoryDao();
				WordDao wordDao = DaoFactory.getWordDao();
				cathDao.getConnection(conn);
				wordDao.getConnection(conn);
				dic.addAllCathegories(cathDao.readAll());
				dic.addAllWords(wordDao.readAll());
				
				DaoFactory.closeConnectionWithSQLiteDB(conn);
				break;
			default:
				break;
		}
	}
}
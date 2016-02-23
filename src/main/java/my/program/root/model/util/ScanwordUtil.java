package my.program.root.model.util;

import java.sql.Connection;
import java.util.*;
import java.util.regex.*;

import my.program.root.model.StorageType;
import my.program.root.model.dao.api.CathegoryDao;
import my.program.root.model.dao.api.WordDao;
import my.program.root.model.dao.impl.DaoFactory;
import my.program.root.model.dictionary.Dictionary;

public final class ScanwordUtil {
		
	public static List<String> findWordsByTemplate (List<String> list, String template) {
		List<String> resultList = new LinkedList<String>();
		Pattern pattern = Pattern.compile(convertTemplate(template));
		for(String word: list) {
			Matcher matcher = pattern.matcher(word);
			if(matcher.matches()) {
				resultList.add(word);
			}
		}
		return resultList;
	}

	private static String convertTemplate(String template) {
		String word = template.toUpperCase().trim();
		StringBuilder buf = new StringBuilder();
		for(int index = 0; index < word.length(); index++) {
			if(template.charAt(index) == '?') {
				buf.append('.');
			} 
			else {
				buf.append(word.charAt(index));
			}
		}
		return buf.toString();
	}

	public static void loadDictionaryFromDB(StorageType sType, Dictionary dic) {
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
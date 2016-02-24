package my.program.root.model.util;

import java.sql.Connection;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.*;

import my.program.root.model.StorageType;
import my.program.root.model.dao.api.*;
import my.program.root.model.dao.impl.DaoFactory;
import my.program.root.model.dictionary.Dictionary;
import my.program.root.model.dictionary.Word;

public final class ScanwordUtil {
		
	public static List<String> findWordsByTemplateAndCathegory (Dictionary dic, List<String> list, String template, String cathegoryName) {
		List<String> wordsFromDictionary = null;
		if(cathegoryName.equals("все")) {
			wordsFromDictionary = dic.getWordsValue();
		}
		else {
			wordsFromDictionary = dic.getWordsByCathegory(cathegoryName);
		}
		
		List<String> resultList = new LinkedList<String>();
		Pattern pattern = Pattern.compile(convertTemplate(template));
		for(String word: list) {
			Matcher matcher = pattern.matcher(word);
			if(matcher.matches()) {
				if(wordsFromDictionary != null) {
					if(wordsFromDictionary.contains(word)){
						resultList.add(word);
					}
				}
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
				for(Word word: dic.getWords()) {
					word.addCath(cathDao.readByWordValue(word.getValue()));
				}
				
				DaoFactory.closeConnectionWithSQLiteDB(conn);
				break;
			default:
				break;
		}
	}
}
package my.program.root.model.dictionary;

import java.util.*;
import java.util.regex.*;

public class Dictionary {

	private Map<Integer, Map<String, Integer>> map;
	private List<Word> wordsList;
	
	private static volatile Dictionary instance;
	
	private Dictionary() {
		map = new HashMap<Integer, Map<String, Integer>>();
		for(int index = 2; index < 25; index++) {
			map.put(index, new HashMap<String, Integer>());
		}
	}
	
	public static Dictionary getInstance() {
		Dictionary localInstance = instance;
		if(localInstance == null) {
			synchronized (Dictionary.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new Dictionary();
				}
			}
		}
		return localInstance;
	}
		
	public void addAllWords(List<Word> words) {
		wordsList = new ArrayList<Word> (words.size());
		wordsList.addAll(words);
		for(int index = 0; index < wordsList.size(); index ++) {
			Word word = wordsList.get(index);
			Map<String,Integer> innerMap = map.get(word.getValue().length());
			innerMap.put(word.getValue(), index);
			map.put(word.getValue().length(), innerMap);
		}
	}
	
	public void addWord(Word word) {
		List<Word> tmp = new ArrayList<Word>(wordsList.size()+1);
		tmp.addAll(wordsList);
		if(!containsWord(word.getValue())) {
			tmp.add(word);
			wordsList = tmp;
		}
	}
	
	/**
	 * Return found word by value
	 * @param wordValue 
	 * @return Word class instance
	 */
	public Word getWord(String wordValue) {
		if(map.get(wordValue.length()).containsKey(wordValue)) {
			return wordsList.get(map.get(wordValue.length()).get(wordValue));
		} else {
			return null;
		}
	}
	
	public boolean containsWord(String wordValue) {
		return map.get(wordValue.length()).containsKey(wordValue);
	}
	
	public void removeWord(String wordValue) {
		wordsList.remove(wordsList.get(map.get(wordValue.length()).get(wordValue)));
		map.get(wordValue.length()).remove(wordValue);
	}
	
	private String convertTemplate(String template) {
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
		
	private List<String> getWordsByCathegory(String cathegoryName) {
		List<String> list = new LinkedList<String>();
		for(Word word: wordsList) {
			if(word.containsCath(cathegoryName)) {
				list.add(word.getValue());
			}
		}
		return list;
	}
	
	/**
	 * Return found words, which contains cathegory with value cathegoryName
	 * and define by pattern template
	 * @param template
	 * @param cathegoryName
	 * @return
	 */
	public List<String> findWordsByTemplateAndCathegory(String template, String cathegoryName) {
		List<String> list = this.getWordsByCathegory(cathegoryName);
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
}
package my.program.root.model.dictionary;

import java.util.*;
import java.util.regex.*;

public class Dictionary {
	/**
	 * map: first parameter describe length words contains in List<Word>,
	 * 		second  - parameter contain Map<String, Integer>, 
	 * 		where String is value word and Integer is count in List<Word>  
	 */
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
	
	public List<String> findWordsByTemplate(String template) {
		List<String> list = new LinkedList<String> ();
		Map<String, Integer> innerMap = map.get(template.length());
		Pattern pattern = Pattern.compile(convertTemplate(template));
		for(String word: innerMap.keySet()) {
			Matcher matcher = pattern.matcher(word);
			if(matcher.matches()) {
				list.add(word);
			}
		}
		return list;
	}
	
	
	public List<String> findWordsByCathegory(String cathegoryName) {
		List<String> list = new LinkedList<String>();
		for(Word word: wordsList) {
			if(word.containsCath(cathegoryName)) {
				list.add(word.getValue());
			}
		}
		return list;
	}
}
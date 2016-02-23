package my.program.root.model.dictionary;

import java.util.*;

import my.program.root.model.util.ScanwordUtil;

public class Dictionary {

	private Map<Integer, Map<String, Integer>> map;
	private List<Word> wordsList;
	private List<Cathegory> cathList;
	
	private static volatile Dictionary instance;
	
	private Dictionary() {
		map = new HashMap<Integer, Map<String, Integer>>();
		for(int index = 2; index < 25; index++) {
			map.put(index, new HashMap<String, Integer>());
		}
		cathList = new ArrayList<Cathegory>(0);
		wordsList = new ArrayList<Word>(0);
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
		List<Word> tmp = null;
		if(wordsList.size() == 0) {
			tmp = new ArrayList<Word> (words.size());
			tmp.addAll(words);
		} else {
			tmp = new ArrayList<Word> (wordsList.size() + words.size());
			tmp.addAll(wordsList);
			tmp.addAll(words);
		}
		wordsList = tmp;
		for(int index = 0; index < wordsList.size(); index ++) {
			Word word = wordsList.get(index);
			if(!map.containsKey(word.getValue())) {
				Map<String, Integer> iMap = map.get(word.getValue().length());
				iMap.put(word.getValue(), index);
				map.put(word.getValue().length(), iMap);
			}
		}
	}
	
	public void addWord(Word word) {
		List<Word> tmp = new ArrayList<Word>(wordsList.size()+1);
		tmp.addAll(wordsList);
		if(!containsWord(word.getValue())) {
			tmp.add(word);
			Map<String, Integer> iMap = map.get(word.getValue().length());
			iMap.put(word.getValue(), tmp.size()-1);
			map.put(word.getValue().length(), iMap);
			wordsList = tmp;
		}
		
	}
	
	public void addAllCathegories(List<Cathegory> caths) {
		cathList = caths;
	}
	
	public List<String> getCathegories() {
		List<String> list = new ArrayList<String>(cathList.size());
		for(int index = 0; index < list.size(); index++) {
			list.set(index, cathList.get(index).getValue());
		}
		return list;
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
		return ScanwordUtil.findWordsByTemplate(getWordsByCathegory(cathegoryName), template);
	}
}
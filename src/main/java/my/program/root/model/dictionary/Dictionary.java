package my.program.root.model.dictionary;

import java.util.*;

public class Dictionary {
	private Map<Integer, Map<String, Integer>> map;
	private List<Word> list;
	
	private static volatile Dictionary instance;
	
	private Dictionary() {
		list = new LinkedList<Word>();
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
		
	public void fillDictionary(List<Word> words) {
		list = words;
		for(Word word: words) {
			int length = word.getValue().length();  
			Map<String, Integer> innerMap = map.get(length);
			innerMap.put(word.getValue(), word.getId());
			map.put(length, innerMap);
		}
	}
	
}

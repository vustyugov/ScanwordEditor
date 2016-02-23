package my.program.root.model.util;

import java.util.*;
import java.util.regex.*;

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

}
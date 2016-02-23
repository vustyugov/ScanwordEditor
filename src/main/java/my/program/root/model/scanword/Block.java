package my.program.root.model.scanword;

import java.util.*;

import my.program.root.model.util.ScanwordUtil;

public class Block {
	private String name;
	private String creationTime;
	private String endTime;
	private Map<String, Scanword> scanwords;
	
	public Block(String name, String creationTime) {
		this.name = name;
		this.creationTime = creationTime;
		scanwords = new HashMap<String, Scanword>();
	}
	
	public void setScanwords(List<Scanword> scanwords) {
		for(Scanword scanword: scanwords) {
			this.scanwords.put(scanword.getName(), scanword);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	public String getEndTime() {
		return endTime;
	}
	
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public List<Scanword> getScanword() {
		return new LinkedList<Scanword>(scanwords.values());
	}
	
	public Scanword getScanword(String scanwordName) {
		return scanwords.get(scanwordName);
	}

	public List<String> getWordsList() {
		List<String> list = new LinkedList<String>();
		for(Scanword scanword: scanwords.values()) {
			list.addAll(scanword.getWords());
		}
		Collections.sort(list);
		return list;
	}
	
	public List<String> findWordsByTemplate(String template) {
		return ScanwordUtil.findWordsByTemplate(getWordsList(), template);
	}
}
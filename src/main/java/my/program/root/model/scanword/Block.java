package my.program.root.model.scanword;

import java.util.*;

public class Block {
	private String name;
	private Date creationTime;
	private Date endTime;
	private Map<String, Scanword> scanwords;
	
	public Block(String name, Date creationTime, BlockType type) {
		this.name = name;
		this.creationTime = creationTime;
		switch(type) {
		case fiftyShortMozaic:
			scanwords = new HashMap<String, Scanword>();
			break;
		default :
			break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
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
}
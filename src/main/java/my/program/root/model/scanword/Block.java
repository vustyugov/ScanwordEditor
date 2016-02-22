package my.program.root.model.scanword;

import java.util.*;
import java.util.regex.Pattern;

public class Block {
	
	private String name;
	private Date creationTime;
	private Date endTime;
	private List<Scanword> scanwords;
	private Map<String, List<String>> wordsList;
	
	public Block(String name, Date creationTime, BlockType type) {
		this.name = name;
		this.creationTime = creationTime;
		switch(type) {
		case fiftyShortMozaic:
			scanwords = new ArrayList<Scanword> (50);
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
		return scanwords;
	}
	
	public Scanword getScanword(int index) {
		return scanwords.get(index);
	}

	public List<String> getWordsList(String scanwordName) {
		return wordsList.get(scanwordName);
	}
}

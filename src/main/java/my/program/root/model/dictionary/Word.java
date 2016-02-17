package my.program.root.model.dictionary;

import java.util.*;

public class Word {
	private int id;
	private String value;
	private List<String> desc;
	private List<Cathegory> cath;
	
	public Word(int id, String value) {
		this.id = id;
		this.value = value;
		this.desc = new LinkedList<String>();
		this.cath = new LinkedList<Cathegory>();
	}
	
	public Word(int id, String value, List<String> desc) {
		this(id, value);
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}
	
	public String getValue() {
		return value;
	}
	
	public List<String> getDesc() {
		return desc;
	}
	
	public String getDesc(int index) {
		return desc.get(index);
	}
	
	public void removeDesc(int index) {
		desc.remove(index);
	}
	
	public void addDesc(String desc) {
		this.desc.add(desc);
	}
	
	public void addDesc(List<String> desc) {
		this.desc.addAll(desc);
	}
	
	public List<Cathegory> getCath() {
		return cath;
	}
	
	public Cathegory getCath(int index) {
		return cath.get(index);
	}
	
	public void removeCath (Cathegory cath) {
		this.cath.remove(cath);
	}
	
	public boolean containsCath(Cathegory cath) {
		if (this.cath.contains(cath)) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("null")
	public boolean containsCath(String cathName) {
		if(cathName != null) {
			return false;
		}
		for(Cathegory element: cath) {
			if(cathName.equals(element.getValue())) {
				return true;
			}
		}
		return false;
	}
	
	public void addCath(Cathegory cath) {
		this.cath.add(cath);
	}
	
	public void addCath(List<Cathegory> cath) {
		this.cath = cath;
	}
}
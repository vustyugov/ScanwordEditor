package my.program.root.model.dictionary;

public class Cathegory {
	private int id;
	private String value;
	private String desc;
	
	public Cathegory (int id, String value) {
		this.id = id;
		this.value = value;
		this.desc = "";
	}
	
	public Cathegory (int id, String value, String desc) {
		this(id, value);
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}
	
	public String getValue () {
		return value;
	}
	
	public void setValue (String value) {
		this.value = value;
	}
	
	public String getDesc () {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if(obj instanceof Cathegory) {
			if(((Cathegory)obj).id == this.id & ((Cathegory)obj).value == this.value) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}

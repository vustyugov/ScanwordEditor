package my.program.root.model.dictionary;

public class Cathegory {
	private int id;
	private String value;
	
	public Cathegory (int id, String value) {
		this.id = id;
		this.value = value;
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
}

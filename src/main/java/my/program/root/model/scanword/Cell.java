package my.program.root.model.scanword;

public class Cell {
	private String string;
	private boolean editable;
	private boolean comment;
	private boolean arrow;
	
	public Cell() {
		string = "";
		editable = true;
		comment = false;
		arrow = false;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean getEditable() {
		return editable;
	}
	
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	
	public boolean getComment() {
		return comment;
	}
	
	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public boolean getArrow() {
		return arrow;
	}
}

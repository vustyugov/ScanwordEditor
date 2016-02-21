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
	
	public void setText(String string) {
		this.string = string;
	}
	
	public String getText() {
		return string;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean isEditable() {
		return editable;
	}
	
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	
	public boolean isComment() {
		return comment;
	}
	
	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public boolean isArrow() {
		return arrow;
	}
}

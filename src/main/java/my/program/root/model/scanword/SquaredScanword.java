package my.program.root.model.scanword;

import java.util.Date;

public abstract class SquaredScanword implements Scanword{

	private static final long serialVersionUID = -6674690312789957146L;
	protected String name;
	protected int columnCount;
	protected int rowCount;
	protected Date creationTime;
	protected Date endTime;
	protected Cell[][] array;
	
	public SquaredScanword(String name, Date date) {
		this.name = name;
		this.creationTime = date;
	}
	
	public SquaredScanword(String name, Cell[][] array, Date date) {
		this.name = name;
		this.creationTime = date;
		this.array = array;
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
	public Cell[][] getArray() {
		return array;
	}
	public void setArray(Cell[][] array) {
		this.array = array;
	}
	public int getColumnCount() {
		return columnCount;
	}
	public int getRowCount() {
		return rowCount;
	}	
	
	public abstract void createTemplate();
}
package my.program.root.model.scanword;

import java.util.*;

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
	
	private String getSymbol(Cell cell) {
		if(cell.isEditable()) {
			if(cell.isComment()) {
				return " ";
			} else {
				return cell.getText();
			}
		} else {
			return "";
		}
	}
	private String getLine() {
		StringBuilder line = new StringBuilder();
		StringBuilder vLine = new StringBuilder();
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				line.append(getSymbol(array[rIndex][cIndex]));
				vLine.append(getSymbol(array[cIndex][rIndex]));
			}
		}
		line.append(" ");
		line.append(vLine.toString());
		return line.toString();		
	}
	private List<String> parseLine(String line) {
		List<String> wordList = new LinkedList<String>();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter("1");
		while (scanner.hasNext()) {
			wordList.add(scanner.next());
		}
		return wordList;
	}
	
	public List<String> getWords() {
		List<String> list = new LinkedList<String>();
		list.addAll(parseLine(getLine()));
		Collections.sort(list);
		return list;
	}
	
	public abstract void createTemplate();
}
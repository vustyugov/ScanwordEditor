package my.program.root.model.scanword;

import java.util.*;

public abstract class SquaredScanword implements Scanword{

	private static final long serialVersionUID = -6674690312789957146L;
	protected String name;
	protected int columnCount;
	protected int rowCount;
	protected String creationTime;
	protected String endTime;
	protected Cell[][] array;
	
	public SquaredScanword(String name, String date) {
		this.name = name;
		this.creationTime = date;
	}
	
	public SquaredScanword(String name, Cell[][] array, String date) {
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
			return " ";
		}
	}
	private String getLine() {
		StringBuilder line = new StringBuilder();
		StringBuilder vLine = new StringBuilder();
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				line.append(getSymbol(array[rIndex][cIndex]));
			}
			line.append(" ");
		}
		for(int rIndex = 0; rIndex < columnCount; rIndex++) {
			for(int cIndex = 0; cIndex < rowCount; cIndex++) {
				vLine.append(getSymbol(array[cIndex][rIndex]));
			}
			vLine.append(" ");
		}

		line.append(" ");
		line.append(vLine.toString());
		return line.toString();		
	}
	private List<String> parseLine(String line) {
		List<String> wordList = new LinkedList<String>();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(" ");
		while (scanner.hasNext()) {
			String word = scanner.next();
			if(word.length() > 1 ) {
				wordList.add(word);
			}
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
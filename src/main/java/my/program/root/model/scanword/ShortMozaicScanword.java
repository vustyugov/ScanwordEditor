package my.program.root.model.scanword;

import java.util.*;

public final class ShortMozaicScanword extends SquaredScanword{

	private static final long serialVersionUID = -6627901701722127760L;

	public ShortMozaicScanword(String name, Date date) {
		super(name, date);
		createTemplate();
	}

	@Override
	public int getId() {
		return -1;
	}

	@Override
	public void setArray(Cell[][] array) {
		super.array = array;		
	}

	@Override
	public void setArrayElement(int columnIndex, int rowIndex, Cell value) {
		array[rowIndex][columnIndex] = value;
	}

	@Override
	public Cell getArrayElement(int columnIndex, int rowIndex) {
		return array[rowIndex][columnIndex];
	}

	@Override
	public List<String> getWords() {
		List<String> list = new LinkedList<String>();
		StringBuilder line = new StringBuilder();
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				Cell cell = array[rIndex][cIndex];
				if(cell.isEditable()) {
					if(cell.isComment()) {
						line.append(" ");
					} else {
						line.append(cell.getText());
					}
				}
			}
		}
		list.addAll(parseLine(line.toString()));
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				Cell cell = array[cIndex][rIndex];
				if(cell.isEditable()) {
					if(cell.isComment()) {
						line.append(" ");
					} else {
						line.append(cell.getText());
					}
				}
			}
		}
		list.addAll(parseLine(line.toString()));
		return list;
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

	@Override
	public void createTemplate() {
		array = new Cell[33][15];
		rowCount = 33;
		columnCount = 15;
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				array[rIndex][cIndex].setEditable(true);
				array[rIndex][cIndex].setComment(false);
				array[rIndex][cIndex].setArrow(false);
			}
		}
	}
}
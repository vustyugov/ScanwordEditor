package my.program.root.model.scanword;

public final class ShortMozaicScanword extends SquaredScanword{

	private static final long serialVersionUID = -6627901701722127760L;

	public ShortMozaicScanword(String name, String string) {
		super(name, string);
		createTemplate();
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
	public void createTemplate() {
		array = new Cell[30][15];
		rowCount = 30;
		columnCount = 15;
		for(int rIndex = 0; rIndex < rowCount; rIndex++) {
			for(int cIndex = 0; cIndex < columnCount; cIndex++) {
				Cell cell = new Cell();
				cell.setEditable(true);
				cell.setComment(false);
				cell.setArrow(false);
				array[rIndex][cIndex] = cell;
			}
		}
	}
}
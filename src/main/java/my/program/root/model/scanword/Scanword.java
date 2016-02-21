package my.program.root.model.scanword;

import java.util.*;

public interface Scanword {
	public int getId();
	public String getName ();
	public int getRowCount();
	public int getColumnCount();
	public void setCreationTime(Date cTime);
	public Date getCreationTime();
	public void setEndTime(Date eTime);
	public Date getEndTime();
	public void setArray(Cell[][] array);
	public Cell[][] getArray();
	public void setArrayElement(int columnIndex, int rowIndex, Cell value);
	public Cell getArrayElement(int columnIndex, int rowIndex);
	public List<String> getWords();
}

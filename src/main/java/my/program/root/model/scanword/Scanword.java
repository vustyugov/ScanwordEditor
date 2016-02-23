package my.program.root.model.scanword;

import java.io.*;
import java.util.*;

public interface Scanword extends Serializable{
	public String getName ();
	public int getRowCount();
	public int getColumnCount();
	public void setCreationTime(String cTime);
	public String getCreationTime();
	public void setEndTime(String eTime);
	public String getEndTime();
	public void setArray(Cell[][] array);
	public Cell[][] getArray();
	public void setArrayElement(int columnIndex, int rowIndex, Cell value);
	public Cell getArrayElement(int columnIndex, int rowIndex);
	public List<String> getWords();
}

package my.program.root.model.scanword;

import java.util.*;

public interface Scanword {
	public void setId(int id);
	public int getId();
	public void setName(String name);
	public String getName ();
	public void setCreationTime(Date cTime);
	public Date getCreationTime();
	public void setEndTime(Date eTime);
	public Date getEndTime();
	public List<String> getWords();
}

package my.program.root.model.dao.api;

import java.util.List;

import my.program.root.model.scanword.Scanword;

public interface ScanwordDao {
	public void getConnetction(Object conn);
	public void create(Scanword scanword);
	public List<Scanword> readAll();
	public Scanword readById(int index);
	public void update(Scanword scanword);
	public void delete(Scanword scanword);
}
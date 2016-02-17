package my.program.root.model.dao.api;


import java.sql.Connection;
import java.util.List;
import my.program.root.model.dictionary.Cathegory;

public interface CathegoryDao {
	public void connectToDataBase (Connection conn);
	public void create(Cathegory cath);
	public List<Cathegory> readAll();
	public Cathegory readById(int id);
	public void update(Cathegory cath);
	public void delete(Cathegory cath);
	public void disconnectFromDataBase ();
}

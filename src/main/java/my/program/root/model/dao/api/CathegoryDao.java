package my.program.root.model.dao.api;


import java.sql.Connection;
import java.util.*;
import my.program.root.model.dictionary.Cathegory;

public interface CathegoryDao {
	public final static String readByWordId = "select c.id, c.value, c.description from categories c, link_wc wc, words w "
			+ "									where wc.category_id = c.id and wc.word_id = w.id and w.value = ";
	public final static String readAllCathegories = "select * from categories;";
	
	public void getConnection (Connection conn);
	public void create(Cathegory cath);
	public List<Cathegory> readAll();
	/**
	 * Create List cathegories by word id
	 * @param id
	 * @return List<Cathegory>
	 */
	public List<Cathegory> readByWordValue(String value);
	public Cathegory readById(int id);
	public void update(Cathegory cath);
	public void delete(Cathegory cath);
}

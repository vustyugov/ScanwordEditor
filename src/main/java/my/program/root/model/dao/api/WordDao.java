package my.program.root.model.dao.api;

import java.sql.*;
import java.util.*;
import my.program.root.model.dictionary.Word;

public interface WordDao {
	public void connectToDataBase(Connection conn);
	public void create(Word word);
	public List<Word> readAll();
	public Word readById(int id);
	public Word readByValue(String value);
	public void update(Word word);
	public void delete(Word word);
	public void disconnectFromDataBase();
}
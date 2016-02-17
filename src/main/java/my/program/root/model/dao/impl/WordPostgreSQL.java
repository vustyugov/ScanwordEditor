package my.program.root.model.dao.impl;

import java.sql.Connection;
import java.util.List;

import my.program.root.model.dao.api.WordDao;
import my.program.root.model.dictionary.Word;

public class WordPostgreSQL implements WordDao {

	@Override
	public void connectToDataBase(Connection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Word> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Word readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Word readByValue(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectFromDataBase() {
		// TODO Auto-generated method stub
		
	}
}
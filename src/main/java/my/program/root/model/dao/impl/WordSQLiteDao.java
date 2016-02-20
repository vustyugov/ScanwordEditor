package my.program.root.model.dao.impl;

import java.io.*;
import java.sql.*;
import java.util.*;
import my.program.root.model.dao.api.WordDao;
import my.program.root.model.dictionary.Word;

public class WordSQLiteDao implements WordDao{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	@Override
	public void getConnection (Connection conn) {
		this.conn = conn; 
	}

	@Override
	public void create(Word word) {
	    String sql = "insert into words (id, value, description) values(?, ?, ?)";
	    
	    ObjectOutputStream oos = null;
	    
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(word.getDesc());
			oos.flush();
			oos.close();
			bos.close();
	    } catch (IOException e) {
			e.printStackTrace();
		}
		    
		byte[] data = bos.toByteArray();
		try {
			ps=conn.prepareStatement(sql);
			ps.setObject(1, word.getId());
			ps.setObject(2, word.getValue());
			ps.setObject(3, data);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private Word readByParam(String param, String paramName) {
		Word word = null;
		StringBuilder buf = new StringBuilder("select * from words where ");
		buf.append(paramName);
		buf.append(" = ");
		buf.append(param);
		buf.append(";");
		String sql = buf.toString();
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		ByteArrayInputStream bais;
		ObjectInputStream ois;
		try{
			word = new Word(rs.getInt("id"), rs.getString("value"));
			bais = new ByteArrayInputStream(rs.getBytes("description"));
			ois = new ObjectInputStream(bais);
			word.addDesc((List<String>)ois.readObject());
		} 
		catch (Exception e) {
            e.printStackTrace();
        } finally {
        	try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }
		return word;
	}

	@Override
	public Word readById(int id) {
		return readByParam(String.valueOf(id), "id");
	}
	
	@Override
	public Word readByValue(String value) {
		return readByParam(value, "value");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Word> readAll() {
		List<Word> list = new LinkedList<Word>();
		String sql = "select * from words;";
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Word word = new Word(rs.getInt("id"), rs.getString("value"));
				ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes("description"));
				ObjectInputStream ois = new ObjectInputStream(bais);
				word.addDesc((List<String>)ois.readObject());
				list.add(word);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public void update(Word word) {
		
	}

	@Override
	public void delete(Word word) {
		StringBuilder buf = new StringBuilder("delete from words where id = ?");
		buf.append(word.getId());
		buf.append(";");
		try {
			ps = conn.prepareStatement(buf.toString());
			ps.setInt(1, word.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
package my.program.root.model.dao.impl;

import java.sql.*;
import java.util.*;
import my.program.root.model.dao.api.CathegoryDao;
import my.program.root.model.dictionary.Cathegory;

public class CathegorySQLiteDao implements CathegoryDao{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public void connectToDataBase(Connection conn) {
		this.conn = conn;		
	}

	@Override
	public void create(Cathegory cath) {
				
	}

	@Override
	public Map<Cathegory, List<Integer>> readAll() {
		Cathegory lastCath = null;
		Cathegory newCath = null;
		Integer index = null;
		Map<Cathegory, List<Integer>> map = new HashMap<Cathegory, List<Integer>>();
		List<Integer> list = new LinkedList<Integer> ();
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(CathegoryDao.readAllCathegories);
			index = new Integer(rs.getInt("word_id"));
			lastCath = new Cathegory(rs.getInt("id"), rs.getString("value"), rs.getString("desc"));
			list.add(index);
			while (rs.next()) {
				index = new Integer(rs.getInt("word_id"));
				newCath = new Cathegory(rs.getInt("id"), rs.getString("value"), rs.getString("desc"));
				if(newCath.equals(lastCath)) {
					list.add(index);
					map.put(newCath, list);
				} 
				else {
					map.put(newCath, list);
					list = new LinkedList<Integer>();
					list.add(index);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	public List<Cathegory> readByWordId(int id) {
		Cathegory cath = null;
		List<Cathegory> list = new LinkedList<Cathegory> ();
		StringBuilder buf = new StringBuilder(CathegoryDao.readByWordId);
		buf.append(id);
		buf.append(";");
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(buf.toString());
			while (rs.next()) {
				cath = new Cathegory(rs.getInt("id"), rs.getString("value"), 
						rs.getString("description"));
				list.add(cath);
			}
		} catch (SQLException e) {
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
	public Cathegory readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cathegory cath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Cathegory cath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnectFromDataBase() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
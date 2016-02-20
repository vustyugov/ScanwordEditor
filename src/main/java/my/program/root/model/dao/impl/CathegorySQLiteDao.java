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
	public void getConnection(Connection conn) {
		this.conn = conn;		
	}

	@Override
	public void create(Cathegory cath) {
				
	}
	

	@Override
	public List<Cathegory> readAll() {
		List<Cathegory> list = new LinkedList<Cathegory>();
		Statement st = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(CathegoryDao.readAllCathegories);
			while (rs.next()) {
				list.add(new Cathegory(rs.getInt("id") ,rs.getString("value"), rs.getString("description")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
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
}
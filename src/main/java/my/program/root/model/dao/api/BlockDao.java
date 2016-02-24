package my.program.root.model.dao.api;

import java.sql.Connection;
import java.util.List;

import my.program.root.model.scanword.Block;

public interface BlockDao {
	public void getConnection(Connection conn);
	public void create(Block block);
	public List<Block> readAll();
	public Block readById(int index);
	public void update(Block block);
	public void delete(Block block);
}
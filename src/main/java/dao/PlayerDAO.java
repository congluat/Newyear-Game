package dao;

import java.util.List;

import model.Player;

public interface PlayerDAO {
	public Player getPlayer(Integer id);
	
	public Player getPlayer(String email);

	public void insert(Player player);

	public void delete(Player player);

	public void update(Player player);

	public List<Player> getAll();
}

package service;

import java.util.List;

import model.Player;

public interface PlayerService {
	Player getPlayer(Integer id);

	Player getPlayer(String email);

	void insert(Player player);

	void delete(Player player);

	void update(Player player);

	List<Player> getAll();
}

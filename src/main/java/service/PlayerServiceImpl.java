package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import dao.PlayerDAO;
import model.Player;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	@Qualifier("playerDAO")
	private PlayerDAO Dao;

	@Override
	public Player getPlayer(Integer id) {
		return Dao.getPlayer(id);
	}

	@Override
	public void insert(Player player) {
		Dao.insert(player);
	}

	@Override
	public void delete(Player player) {
		Dao.delete(player);
	}

	@Override
	public void update(Player player) {
		Dao.update(player);
	}

	@Override
	public List<Player> getAll() {
		return Dao.getAll();
	}

	@Override
	public Player getPlayer(String email) {
		return Dao.getPlayer(email);
	}

}

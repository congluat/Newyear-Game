package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import model.Player;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Player getPlayer(Integer id) {
		Session session = getSessionFactory().openSession();
		Player player = (Player) session.get(Player.class, id);
		session.close();
		return player;
	}

	@Override
	public void insert(Player player) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(player);
		tx.commit();
		session.close();

	}

	@Override
	public void delete(Player player) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(player);
		tx.commit();
		session.close();

	}

	@Override
	public void update(Player player) {
		Session session = getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(player);
		tx.commit();
		session.close();
	}

	@Override
	public List<Player> getAll() {
		Session session = getSessionFactory().openSession();
		List<Player> list = session.createQuery("from Player").list();
		session.close();
		return list;
	}

	@Override
	public Player getPlayer(String email) {
		Session session = getSessionFactory().openSession();
		Player player = (Player) session.createQuery("from Player WHERE email LIKE '%"+email+"%'").uniqueResult();
		session.close();
		return player;
	}

}

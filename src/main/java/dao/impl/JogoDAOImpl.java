package dao.impl;
import org.springframework.stereotype.Repository;
import dao.JogoDAO;
import modelo.Jogo;

@Repository
public abstract class JogoDAOImpl extends JPADaoGenerico<Jogo, Long> implements JogoDAO{
	public JogoDAOImpl() {
		super(Jogo.class);
	}

}

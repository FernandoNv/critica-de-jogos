package dao.impl;
import org.springframework.stereotype.Repository;
import dao.CriticaDAO;
import modelo.Critica;

@Repository
public abstract class CriticaDAOImpl extends JPADaoGenerico<Critica, Long> implements CriticaDAO{
	public CriticaDAOImpl() {
		super(Critica.class);
	}

}

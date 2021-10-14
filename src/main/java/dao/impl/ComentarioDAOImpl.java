package dao.impl;
import org.springframework.stereotype.Repository;
import dao.ComentarioDAO;
import modelo.Comentario;

@Repository
public abstract class ComentarioDAOImpl extends JPADaoGenerico<Comentario, Long> implements ComentarioDAO{
	public ComentarioDAOImpl() {
		super(Comentario.class);
	}

}

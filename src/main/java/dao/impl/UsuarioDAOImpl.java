package dao.impl;
import org.springframework.stereotype.Repository;
import dao.UsuarioDAO;
import modelo.Usuario;

@Repository
public abstract class UsuarioDAOImpl extends JPADaoGenerico<Usuario, Long> implements UsuarioDAO{
	public UsuarioDAOImpl() {
		super(Usuario.class);
	}

}

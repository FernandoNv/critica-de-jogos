package service;
import java.util.List;
import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioAppService {
	long inclui(Usuario usuario);
	void altera(Usuario usuario) throws UsuarioNaoEncontradoException;
	void exclui(Usuario usuario) throws UsuarioNaoEncontradoException;
	
	Usuario recuperaUsuario(long id) throws UsuarioNaoEncontradoException;
	Usuario recuperaUsuarioCriticas(long id) throws UsuarioNaoEncontradoException;
	
	List<Usuario> recuperaListaUsuariosCriticas();

}

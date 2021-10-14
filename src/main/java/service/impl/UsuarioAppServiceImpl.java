package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Usuario;
import service.UsuarioAppService;

public class UsuarioAppServiceImpl implements UsuarioAppService{
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public long inclui(Usuario usuario) {
		usuarioDAO.inclui(usuario);
		return usuario.getId();
	}

	@Transactional
	public void altera(Usuario usuario) throws UsuarioNaoEncontradoException {
		try {
			usuarioDAO.getPorIdComLock(usuario.getId());
			usuarioDAO.altera(usuario);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}

	@Transactional
	public void exclui(Usuario usuario) throws UsuarioNaoEncontradoException {
		try {
			Usuario usuarioExcluir = usuarioDAO.recuperaUsuarioCriticas(usuario.getId());
			if(usuarioExcluir.getCriticas().size() > 0) {
				throw new UsuarioNaoEncontradoException("Usuario possui criticas e nao pode ser removido");
			}
			
			usuarioDAO.exclui(usuarioExcluir);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado");
		}
	}
	
	@Override
	public Usuario recuperaUsuario(long id) throws UsuarioNaoEncontradoException {
		try {
			return usuarioDAO.getPorId(id);
		} catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}

	@Override
	public Usuario recuperaUsuarioCriticas(long id) throws UsuarioNaoEncontradoException{
		try {
			return usuarioDAO.recuperaUsuarioCriticas(id);
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario não encontrado");
		}
	}

	@Override
	public List<Usuario> recuperaListaUsuariosCriticas(){
		System.out.println(usuarioDAO.getClass().getName() + " - " + usuarioDAO.getClass().hashCode());
		
		return usuarioDAO.recuperaListaUsuariosCriticas();
	}

}

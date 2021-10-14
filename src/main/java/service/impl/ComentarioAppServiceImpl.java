package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.ComentarioDAO;
import dao.CriticaDAO;
import dao.UsuarioDAO;
import excecao.ComentarioNaoEncontradoException;
import excecao.CriticaNaoEncontradaException;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Comentario;
import modelo.Critica;
import modelo.Usuario;
import service.ComentarioAppService;

public class ComentarioAppServiceImpl implements ComentarioAppService{
	
	@Autowired
	private ComentarioDAO comentarioDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private CriticaDAO criticaDAO;
	
	@Transactional
	public long inclui(Comentario comentario) throws UsuarioNaoEncontradoException, CriticaNaoEncontradaException {
		Usuario usuario = comentario.getAutor();
		Critica critica = comentario.getCritica();
		
		try {
			usuario = usuarioDAO.getPorIdComLock(usuario.getId());
			
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}
		
		try {
			critica = criticaDAO.getPorIdComLock(critica.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica nao encontrada");
		}
		
		Comentario novoComentario = comentarioDAO.inclui(comentario);
		
		return novoComentario.getId();
		
	}
	
	@Transactional
	public void altera(Comentario comentario) throws ComentarioNaoEncontradoException, UsuarioNaoEncontradoException, CriticaNaoEncontradaException {
		Usuario usuario = comentario.getAutor();
		Critica critica = comentario.getCritica();
		
		try {
			usuario = usuarioDAO.getPorIdComLock(usuario.getId());
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}

		try {
			critica = criticaDAO.getPorIdComLock(critica.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica nao encontrada");
		}
		
		try {
			comentarioDAO.getPorIdComLock(comentario.getId());
			comentarioDAO.altera(comentario);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ComentarioNaoEncontradoException("Comentario nao encontrado");
		}
		
	}
	
	@Transactional
	public void exclui(Comentario comentario) throws ComentarioNaoEncontradoException {
		try {
			Comentario comentarioExcluir = comentarioDAO.getPorId(comentario.getId());
			comentarioDAO.exclui(comentarioExcluir);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ComentarioNaoEncontradoException("Comentario nao encontrado");
		}
		
	}
	
	@Override
	public Comentario recuperaComentario(long id) throws ComentarioNaoEncontradoException {
		try {
			return comentarioDAO.getPorId(id);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ComentarioNaoEncontradoException("Comentario nao encontrado");
		}
	}
	
	@Override
	public Comentario recuperaComentarioAutorCritica(long id) throws ComentarioNaoEncontradoException {
		try {
			return comentarioDAO.recuperaComentarioAutorCritica(id);
		}catch(ObjetoNaoEncontradoException e) {
			throw new ComentarioNaoEncontradoException("Comentario nao encontrado");
		}
	}
	
	@Override
	public List<Comentario> recuperaListaComentariosAutorCritica() {
		System.out.println(comentarioDAO.getClass().getName() + " - " + comentarioDAO.getClass().hashCode());
		
		return comentarioDAO.recuperaListaComentariosAutorCritica();
	}
	

}

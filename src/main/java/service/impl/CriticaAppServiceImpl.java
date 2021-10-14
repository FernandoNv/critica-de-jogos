package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.CriticaDAO;
import dao.JogoDAO;
import dao.UsuarioDAO;
import excecao.CriticaNaoEncontradaException;
import excecao.JogoNaoEncontradoException;
import excecao.ObjetoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Critica;
import modelo.Jogo;
import modelo.Usuario;
import service.CriticaAppService;

public class CriticaAppServiceImpl implements CriticaAppService{
	
	@Autowired
	private CriticaDAO criticaDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private JogoDAO jogoDAO;
	
	
	@Transactional
	public long inclui(Critica critica) throws UsuarioNaoEncontradoException, JogoNaoEncontradoException{
		Usuario usuario = critica.getAutor();
		Jogo jogo = critica.getJogo();
		
		try {
			usuario = usuarioDAO.getPorIdComLock(usuario.getId());
			
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}
		
		try {
			jogo = jogoDAO.getPorIdComLock(jogo.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo nao encontrado");
		}
		
		Critica novaCritica = criticaDAO.inclui(critica);
		
		return novaCritica.getId();
	}
	
	@Transactional
	public void exclui(Critica critica) throws CriticaNaoEncontradaException {
		try {
			Critica criticaExcluir = criticaDAO.getPorId(critica.getId());
			criticaDAO.exclui(criticaExcluir);
		}catch(ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica não encontrada");
		}
	}
	
	@Transactional
	public void altera(Critica critica) throws CriticaNaoEncontradaException, UsuarioNaoEncontradoException, JogoNaoEncontradoException {
		Usuario usuario = critica.getAutor();
		Jogo jogo = critica.getJogo();
		
		try {
			usuario = usuarioDAO.getPorIdComLock(usuario.getId());
		}catch(ObjetoNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("Usuario nao encontrado");
		}

		try {
			jogo = jogoDAO.getPorIdComLock(jogo.getId());
		} catch (ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo nao encontrado");
		}
		
		try {
			criticaDAO.getPorIdComLock(critica.getId());
			criticaDAO.altera(critica);
		}catch(ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica nao encontrado");
		}
	}

	@Override
	public Critica recuperaCritica(long id) throws CriticaNaoEncontradaException {
		try {
			return criticaDAO.getPorId(id);
		} catch(ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica nao encontrado");
		}
	}

	@Override
	public Critica recuperaCriticaAutorJogo(long id) throws CriticaNaoEncontradaException {
		try {
			return criticaDAO.recuperaCriticaAutorJogo(id);
		} catch(ObjetoNaoEncontradoException e) {
			throw new CriticaNaoEncontradaException("Critica nao encontrado");
		}
	}

	@Override
	public List<Critica> recuperaListaCriticasAutorJogo() {
		System.out.println(criticaDAO.getClass().getName() + " - " + criticaDAO.getClass().hashCode());
		
		return criticaDAO.recuperaListaCriticasAutorJogo();
	}

}

package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.JogoDAO;
import excecao.ObjetoNaoEncontradoException;
import excecao.JogoNaoEncontradoException;
import modelo.Jogo;
import service.JogoAppService;

public class JogoAppServiceImpl implements JogoAppService{
	
	@Autowired
	private JogoDAO jogoDAO;
	
	@Transactional
	public long inclui(Jogo jogo) {
		jogoDAO.inclui(jogo);
		return jogo.getId();
	}

	@Transactional
	public void altera(Jogo jogo) throws JogoNaoEncontradoException {
		try {
			jogoDAO.getPorIdComLock(jogo.getId());
			jogoDAO.altera(jogo);
		}catch(ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo não encontrado");
		}
	}

	@Transactional
	public void exclui(Jogo jogo) throws JogoNaoEncontradoException {
		try {
			Jogo jogoExcluir = jogoDAO.recuperaJogoECriticas(jogo.getId());
			
			if(jogoExcluir.getCriticas().size() > 0) {
				throw new JogoNaoEncontradoException("Jogo possui criticas e nao pode ser removido");
			}
			
			jogoDAO.exclui(jogoExcluir);
		}catch(ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo não encontrado");
		}
	}
	
	@Override
	public Jogo recuperaJogo(long id) throws JogoNaoEncontradoException {
		try {
			return jogoDAO.getPorId(id);
		} catch(ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo não encontrado");
		}
	}

	@Override
	public Jogo recuperaJogoECriticas(long id) throws JogoNaoEncontradoException {
		try {
			return jogoDAO.recuperaJogoECriticas(id);
		}catch(ObjetoNaoEncontradoException e) {
			throw new JogoNaoEncontradoException("Jogo não encontrado");
		}
	}

	@Override
	public List<Jogo> recuperaListaJogosECriticas() {
		System.out.println(jogoDAO.getClass().getName() + " - " + jogoDAO.getClass().hashCode());
		
		return jogoDAO.recuperaListaJogosECriticas();
	}
	
	

}

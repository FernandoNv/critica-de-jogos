package service;
import java.util.List;
import excecao.JogoNaoEncontradoException;
import modelo.Jogo;

public interface JogoAppService {
	long inclui(Jogo jogo);
	void altera(Jogo jogo) throws JogoNaoEncontradoException;
	void exclui(Jogo jogo) throws JogoNaoEncontradoException;
	
	Jogo recuperaJogo(long id) throws JogoNaoEncontradoException;
	Jogo recuperaJogoECriticas(long id) throws JogoNaoEncontradoException;
	
	List<Jogo> recuperaListaJogosECriticas();
}

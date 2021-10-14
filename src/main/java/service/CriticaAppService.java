package service;
import java.util.List;

import excecao.CriticaNaoEncontradaException;
import excecao.JogoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Critica;

public interface CriticaAppService {
	long inclui(Critica critica) throws UsuarioNaoEncontradoException, JogoNaoEncontradoException;
	void altera(Critica critica) throws CriticaNaoEncontradaException, UsuarioNaoEncontradoException, JogoNaoEncontradoException;
	void exclui(Critica critica) throws CriticaNaoEncontradaException;
	
	Critica recuperaCritica(long id) throws CriticaNaoEncontradaException;
	Critica recuperaCriticaAutorJogo(long id) throws CriticaNaoEncontradaException;

	List<Critica> recuperaListaCriticasAutorJogo();
}

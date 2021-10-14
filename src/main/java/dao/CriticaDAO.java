package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Critica;

public interface CriticaDAO extends DaoGenerico<Critica, Long>{
	/* ****** M�todos Gen�ricos ******* */
	
	@RecuperaObjeto
	Critica recuperaCriticaAutorJogo(long id) throws ObjetoNaoEncontradoException;

	@RecuperaUltimoOuPrimeiro
	Critica recuperaUltimaCritica(Critica critica) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Critica> recuperaListaCriticas();
	
	@RecuperaLista
	List<Critica> recuperaListaCriticasAutorJogo();
	
//	@RecuperaUltimoOuPrimeiro
//	Produto recuperaPrimeiroProduto() throws ObjetoNaoEncontradoException;

//	@RecuperaLista
//	List<Produto> recuperaListaDeProdutosELances();
//
//	@RecuperaConjunto
//	Set<Produto> recuperaConjuntoDeProdutosELances();
	
	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
}

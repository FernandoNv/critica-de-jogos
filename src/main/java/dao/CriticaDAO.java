package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Critica;

public interface CriticaDAO extends DaoGenerico<Critica, Long>{
	/* ****** Métodos Genéricos ******* */
	
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
	
	/* ****** Métodos não Genéricos ******* */

	// Um método definido aqui, que não seja anotado, deverá ser
	// implementado como final em ProdutoDAOImpl.
}

package dao;

import java.util.List;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Jogo;

public interface JogoDAO extends DaoGenerico<Jogo, Long>{
	/* ****** Métodos Genéricos ******* */
	@RecuperaObjeto
	Jogo recuperaJogoECriticas(long id) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Jogo> recuperaListaJogos();
	
	@RecuperaLista
	List<Jogo> recuperaListaJogosECriticas();

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

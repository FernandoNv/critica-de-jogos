package dao;

import java.util.List;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Jogo;

public interface JogoDAO extends DaoGenerico<Jogo, Long>{
	/* ****** M�todos Gen�ricos ******* */
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
	
	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
}

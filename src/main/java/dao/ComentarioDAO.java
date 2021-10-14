package dao;

import java.util.List;

import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import anotacao.RecuperaUltimoOuPrimeiro;
import excecao.ObjetoNaoEncontradoException;
import modelo.Comentario;

public interface ComentarioDAO extends DaoGenerico<Comentario, Long>{
	/* ****** Métodos Genéricos ******* */
	
	@RecuperaObjeto
	Comentario recuperaComentarioAutorCritica(long id) throws ObjetoNaoEncontradoException;

	@RecuperaUltimoOuPrimeiro
	Comentario recuperaUltimoComentario(Comentario comentario) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Comentario> recuperaListaComentarios();
	
	@RecuperaLista
	List<Comentario> recuperaListaComentariosAutorCritica();
	
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

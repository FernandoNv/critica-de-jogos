package dao;

import java.util.List;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long>{
	/* ****** Métodos Genéricos ******* */
	@RecuperaObjeto
	Usuario recuperaUsuarioCriticas(long id) throws ObjetoNaoEncontradoException;

	@RecuperaLista
	List<Usuario> recuperaListaUsuarios();
	
	@RecuperaLista
	List<Usuario> recuperaListaUsuariosCriticas();
	
	
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

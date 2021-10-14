package dao;

import java.util.List;
import anotacao.RecuperaLista;
import anotacao.RecuperaObjeto;
import excecao.ObjetoNaoEncontradoException;
import modelo.Usuario;

public interface UsuarioDAO extends DaoGenerico<Usuario, Long>{
	/* ****** M�todos Gen�ricos ******* */
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
	
	/* ****** M�todos n�o Gen�ricos ******* */

	// Um m�todo definido aqui, que n�o seja anotado, dever� ser
	// implementado como final em ProdutoDAOImpl.
}

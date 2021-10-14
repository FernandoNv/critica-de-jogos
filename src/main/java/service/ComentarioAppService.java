package service;
import java.util.List;

import excecao.ComentarioNaoEncontradoException;
import excecao.CriticaNaoEncontradaException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Comentario;

public interface ComentarioAppService {
	long inclui(Comentario comentario) throws UsuarioNaoEncontradoException, CriticaNaoEncontradaException;
	void altera(Comentario comentario) throws ComentarioNaoEncontradoException, UsuarioNaoEncontradoException, CriticaNaoEncontradaException;
	void exclui(Comentario comentario) throws ComentarioNaoEncontradoException;
	
	Comentario recuperaComentario(long id) throws ComentarioNaoEncontradoException;
	Comentario recuperaComentarioAutorCritica(long id) throws ComentarioNaoEncontradoException;

	List<Comentario> recuperaListaComentariosAutorCritica();
}

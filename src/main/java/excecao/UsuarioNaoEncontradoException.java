package excecao;

public class UsuarioNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException() {
		super();
	}
	
	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}
}

package excecao;

public class ComentarioNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ComentarioNaoEncontradoException() {
		super();
	}
	
	public ComentarioNaoEncontradoException(String msg) {
		super(msg);
	}
}

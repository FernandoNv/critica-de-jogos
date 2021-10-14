package excecao;

public class JogoNaoEncontradoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public JogoNaoEncontradoException() {
		super();
	}
	
	public JogoNaoEncontradoException(String msg) {
		super(msg);
	}
}

package excecao;

public class CriticaNaoEncontradaException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public CriticaNaoEncontradaException() {
		super();
	}
	
	public CriticaNaoEncontradaException(String msg) {
		super(msg);
	}
}

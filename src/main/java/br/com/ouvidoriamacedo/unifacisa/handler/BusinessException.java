package br.com.ouvidoriamacedo.unifacisa.handler;

// Classe para exceção personalizada
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException(String mensagem) {
		super(mensagem);
	}

	public BusinessException(String mensagem, Object... params) {
		super(String.format(mensagem, params));
	}
}

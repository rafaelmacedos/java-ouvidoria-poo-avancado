package br.com.ouvidoriamacedo.unifacisa.util;

public enum TipoOcorrencia {
	ELOGIO(1),
	RECLAMACAO(2),
	SUGESTAO(3),
	DENUNCIA(4);
	
	private int code;
	
	private TipoOcorrencia(int code) {
		this.code = code;
	}
	
	
	public int getCode() {
		return code;
	}
	
	public static TipoOcorrencia valueOf(int code) {
		for (TipoOcorrencia value : TipoOcorrencia.values()) {
			if (code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("TipoOcorrencia Inválido");
	}
}

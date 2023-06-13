package br.com.ouvidoriamacedo.unifacisa.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.ouvidoriamacedo.unifacisa.util.TipoOcorrencia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Ocorrencia {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;

	@Column(name = "tipo", nullable = false)
	private Integer tipoOcorrencia;

	@Column(name = "mensagem", nullable = false)
	@Getter
	@Setter
	private String mensagem;

	@Column(name = "dataHora", nullable = false)
	@Getter
	@Setter
	private LocalDateTime dataHora;

	@ManyToOne
	@JoinColumn(nullable = false)
	@Getter
	@Setter
	private Usuario usuario;

	public Ocorrencia() {
	}

	public Ocorrencia(TipoOcorrencia tipoOcorrencia, String mensagem, Usuario usuario) {
		setTipoOcorrencia(tipoOcorrencia);
		this.mensagem = mensagem;
		this.dataHora = LocalDateTime.now();
		this.usuario = usuario;
	}

	public TipoOcorrencia getTipoOcorrencia() {
		return TipoOcorrencia.valueOf(tipoOcorrencia);
	}

	public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
		if (tipoOcorrencia != null) {
			this.tipoOcorrencia = tipoOcorrencia.getCode();
		}

	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		return "ID - " + getId() + " - " + getTipoOcorrencia().toString() + " - " + getMensagem()
				+ " - "
				+ getDataHora().format(formatter)
				+ " - "
				+ getUsuario().getNome();
	}

}

package br.com.ouvidoriamacedo.unifacisa.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Pessoa{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "nome", length = 50)
	private String nome;

	@Column(name = "email", length = 70, unique = true)
	private String email;

	@Column(name = "senha")
	private String senha;
	
	@Column(name = "acessoCompleto")
	private Boolean acessoCompleto;

	public Pessoa(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.acessoCompleto = false;
	}

	public Pessoa() {
		this.acessoCompleto = false;
	}
	
	

}

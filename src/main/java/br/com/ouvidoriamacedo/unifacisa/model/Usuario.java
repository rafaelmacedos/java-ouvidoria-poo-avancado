package br.com.ouvidoriamacedo.unifacisa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa{
	
	public Usuario(String nome, String email, String senha) {
		super(nome, email, senha);
	}
	
	public Usuario() {}

	@Override
	public String toString() {
		return "Id do Usuário: "
				+ this.getId()
				+ "\nNome: "
				+ this.getNome()
				+ "\nEmail: "
				+ this.getEmail()
				+"";
	}
	
	
	
}

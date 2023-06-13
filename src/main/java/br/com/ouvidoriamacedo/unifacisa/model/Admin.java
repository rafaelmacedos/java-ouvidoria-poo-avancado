package br.com.ouvidoriamacedo.unifacisa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Pessoa {
	
	public Admin() {}
	
	public Admin(String nome, String email, String senha) {
		super(nome, email, senha);
		this.setAcessoCompleto(true);
	}
	
	@Override
	public String toString() {
		return "Id do Admin: "
				+ this.getId()
				+ "\nNome: "
				+ this.getNome()
				+ "\nEmail: "
				+ this.getEmail()
				+"";
	}
	
}

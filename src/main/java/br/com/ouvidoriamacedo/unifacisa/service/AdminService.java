package br.com.ouvidoriamacedo.unifacisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ouvidoriamacedo.unifacisa.handler.BusinessException;
import br.com.ouvidoriamacedo.unifacisa.model.Admin;
import br.com.ouvidoriamacedo.unifacisa.repository.AdminRepository;
import br.com.ouvidoriamacedo.unifacisa.util.PasswordValidation;

/**
 * @author maced
 * 
 *         Essa é a camada de serviço referente a entidade Admin.
 * 
 *         Aqui são chamados todos os metódos CRUD com o auxílio da interface
 *         AdminRepository que é "instanciada" pela injeção de dependências do
 *         Spring com o uso da anotação "@autowired".
 */

@Service
public class AdminService {
	@Autowired
	AdminRepository repository;

	public boolean salvar(String nome, String email, String senha) {
		try {
			if (nome.isEmpty()) {
				throw new BusinessException("\nO Campo Nome está vazio, tente novamente");
			}
			if (email.isEmpty() | !email.contains("@")) {
				throw new BusinessException("\nO Campo Email está vazio ou não possui @");
			}
			if (senha.isEmpty() | !PasswordValidation.validarSenha(senha)) {
				throw new BusinessException("\nO Campo Senha está vazio ou não atende o padrão necessário");
			}
			BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();
			String senhaCriptografada = cripto.encode(senha);
			Admin novoAdmin = new Admin(nome, email, senhaCriptografada);
			repository.save(novoAdmin);
			return true;
		}

		catch (DataIntegrityViolationException ex) {
			System.out.println("Já existe uma conta cadastrada com esse e-mail");
		}

		catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

		catch (Exception e) {
			System.out.println("Algo deu errado, tente novamente.");
		}
		return false;
	}

	public void listarTodos() {
		for (Admin admin : repository.findAll()) {
			if (admin != null) {
				System.out.println(admin.toString());
			} else {
				System.out.println("Não existem admins cadastrados.");
			}
		}
	}

	public boolean autenticarAdmin(String email, String senha) {
		Admin admin = repository.findByEmail(email);
		BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();
		if (admin != null) {
			admin.setSenha(senha);
			for (Admin adm : repository.findAll()) {
				if (adm.getEmail().equals(adm.getEmail()) & cripto.matches(adm.getSenha(), adm.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}
}

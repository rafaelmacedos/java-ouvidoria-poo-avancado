package br.com.ouvidoriamacedo.unifacisa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ouvidoriamacedo.unifacisa.handler.BusinessException;
import br.com.ouvidoriamacedo.unifacisa.model.Usuario;
import br.com.ouvidoriamacedo.unifacisa.repository.UsuarioRepository;
import br.com.ouvidoriamacedo.unifacisa.util.PasswordValidation;

/**
 * @author maced
 * 
 *         Essa é a camada de serviço referente a entidade Usuario.
 * 
 *         Aqui são chamados todos os metódos CRUD com o auxílio da interface
 *         UsuarioRepository que é "instanciada" pela injeção de dependências do
 *         Spring com o uso da anotação "@autowired".
 */

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

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
			Usuario novoUsuario = new Usuario(nome, email, senhaCriptografada);
			repository.save(novoUsuario);
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
		for (Usuario user : repository.findAll()) {
			if (user != null) {
				System.out.println(user.toString());
			} else {
				System.out.println("Não existem usuários cadastrados.");
			}
		}
	}

	public boolean autenticarUsuario(String email, String senha) {
		Usuario usuario = repository.findByEmail(email);
		BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();
		if (usuario != null) {
			usuario.setSenha(senha);
			for (Usuario user : repository.findAll()) {
				if (usuario.getEmail().equals(user.getEmail()) & cripto.matches(usuario.getSenha(), user.getSenha())) {
					return true;
				}
			}
		}
		return false;
	}

}

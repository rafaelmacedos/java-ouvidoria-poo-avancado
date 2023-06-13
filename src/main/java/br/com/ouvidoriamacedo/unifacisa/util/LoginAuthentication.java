package br.com.ouvidoriamacedo.unifacisa.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ouvidoriamacedo.unifacisa.model.Admin;
import br.com.ouvidoriamacedo.unifacisa.model.Pessoa;
import br.com.ouvidoriamacedo.unifacisa.model.Usuario;
import br.com.ouvidoriamacedo.unifacisa.repository.AdminRepository;
import br.com.ouvidoriamacedo.unifacisa.repository.UsuarioRepository;

@Service
public class LoginAuthentication {
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public Pessoa autenticar(String email, String senha) {
		Admin adm = adminRepository.findByEmail(email);
		Usuario user = usuarioRepository.findByEmail(email);
		BCryptPasswordEncoder cripto = new BCryptPasswordEncoder();
		if (adm != null) {
			adm.setSenha(senha);
			for (Admin admin : adminRepository.findAll()) {
				if (adm.getEmail().equals(admin.getEmail()) & cripto.matches(adm.getSenha(), admin.getSenha())) {
					adm = admin;
				}
			}
			return adm;
		}

		else if (user != null) {
			user.setSenha(senha);
			for (Usuario usuario : usuarioRepository.findAll()) {
				if (user.getEmail().equals(user.getEmail()) & cripto.matches(user.getSenha(), usuario.getSenha())) {
					user = usuario;
				}
			}

			return user;
		}
		return null;

	}

}

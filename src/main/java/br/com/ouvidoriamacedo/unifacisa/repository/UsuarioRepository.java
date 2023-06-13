package br.com.ouvidoriamacedo.unifacisa.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ouvidoriamacedo.unifacisa.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Usuario findByEmail(String email);

}

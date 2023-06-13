package br.com.ouvidoriamacedo.unifacisa.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ouvidoriamacedo.unifacisa.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
	
	Admin findByEmail(String email);
}

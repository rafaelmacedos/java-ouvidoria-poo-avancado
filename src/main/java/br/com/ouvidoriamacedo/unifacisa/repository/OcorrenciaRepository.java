package br.com.ouvidoriamacedo.unifacisa.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import br.com.ouvidoriamacedo.unifacisa.model.Ocorrencia;

public interface OcorrenciaRepository extends CrudRepository<Ocorrencia, Integer> {
	
	ArrayList<Ocorrencia> findByTipoOcorrencia(int code);
	
//	@Transactional
//	void update(Ocorrencia ocorrencia);
}

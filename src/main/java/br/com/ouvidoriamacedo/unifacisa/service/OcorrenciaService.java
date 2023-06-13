package br.com.ouvidoriamacedo.unifacisa.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ouvidoriamacedo.unifacisa.model.Ocorrencia;
import br.com.ouvidoriamacedo.unifacisa.model.Usuario;
import br.com.ouvidoriamacedo.unifacisa.repository.OcorrenciaRepository;
import br.com.ouvidoriamacedo.unifacisa.util.TipoOcorrencia;

/**
 * @author maced
 * 
 *         Essa é a camada de serviço referente a entidade Ocorrencia.
 * 
 *         Aqui são chamados todos os metódos CRUD com o auxílio da interface
 *         OcorrenciaRepository que é "instanciada" pela injeção de dependências
 *         do Spring com o uso da anotação "@autowired".
 */

@Service
public class OcorrenciaService {
	@Autowired
	OcorrenciaRepository repository;

	// Metódos Usuário
	// OBSERVAR
	public void salvar(TipoOcorrencia tipo, String mensagem, Usuario usuario) {
		Ocorrencia novaOcorrencia = new Ocorrencia(tipo, mensagem, usuario);
		repository.save(novaOcorrencia);
	}

	// FEITO OK
	public boolean atualizarPeloId(int id, Usuario usuario, String texto) {
		Optional<Ocorrencia> opcional = repository.findById(id);
		if (opcional.isPresent() & opcional.get().getUsuario().getId() == usuario.getId()) {
			Ocorrencia ocorrencia = opcional.get();
			ocorrencia.setId(ocorrencia.getId());
			ocorrencia.setTipoOcorrencia(ocorrencia.getTipoOcorrencia());
			ocorrencia.setUsuario(ocorrencia.getUsuario());
			ocorrencia.setMensagem(texto);
			ocorrencia.setDataHora(LocalDateTime.now());
			repository.save(ocorrencia);
			return true;
		}

		return false;
	}

	// FEITO OK
	public boolean listarTodos(Usuario usuario) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository.findAll();
		Boolean booleano = false;

		if (!listaOcorrencias.isEmpty()) {
			if (usuario.getAcessoCompleto() == true) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					System.out.println(ocorrencia.toString());
					booleano = true;
				}
			} else if (usuario.getAcessoCompleto() == false) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					if (ocorrencia.getUsuario().getId() == usuario.getId()) {
						System.out.println(ocorrencia.toString());
						booleano = true;
					}
				}
			}
		}
		return booleano;
	}

	// FEITO OK
	public boolean listarPeloTipo(TipoOcorrencia tipo, Usuario usuario) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository
				.findByTipoOcorrencia(tipo.getCode());
		Boolean booleano = false;

		if (!listaOcorrencias.isEmpty()) {
			if (usuario.getAcessoCompleto() == true) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					System.out.println(ocorrencia.toString());
					booleano = true;
				}
			} else if (usuario.getAcessoCompleto() == false) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					if (ocorrencia.getUsuario().getId() == usuario.getId()) {
						System.out.println(ocorrencia.toString());
						booleano = true;
					}
				}
			}
		}
		return booleano;
	}

	// FEITO OK
	public boolean listarPeloId(int id, Usuario usuario) {
		Optional<Ocorrencia> opcional = repository.findById(id);
		if (opcional.isPresent() & opcional.get().getUsuario().getId() == usuario.getId()
				|| opcional.isPresent() & usuario.getAcessoCompleto() == true) {
			Ocorrencia ocorrencia = opcional.get();
			System.out.println(ocorrencia.toString());
			return true;
		} else {
			System.out.println("Não foi encontrada nenhuma ocorrência com esse Id");
			return false;
		}
	}

	// FEITO OK*
	public boolean excluirPeloTipo(TipoOcorrencia tipo, Usuario usuario) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository
				.findByTipoOcorrencia(tipo.getCode());
		Boolean booleano = false;

		if (!listaOcorrencias.isEmpty()) {
			if (usuario.getAcessoCompleto() == true) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					repository.delete(ocorrencia);
					booleano = true;
				}
			} else if (usuario.getAcessoCompleto() == false) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					if (ocorrencia.getUsuario().getId() == usuario.getId()) {
						repository.delete(ocorrencia);
						booleano = true;
					}
				}
			}
		}

		return booleano;
	}

	// FEITO OK*
	public boolean excluirPeloId(int id, Usuario usuario) {
		Optional<Ocorrencia> opcional = repository.findById(id);
		if (opcional.isPresent() & opcional.get().getUsuario().getId() == usuario.getId()
				|| opcional.isPresent() & usuario.getAcessoCompleto() == true) {
			Ocorrencia ocorrencia = opcional.get();
			repository.delete(ocorrencia);
			return true;
		} else {
			System.out.println("Não foi encontrada nenhuma ocorrência com esse Id");
			return false;
		}
	}

	// FEITO OK*
	public boolean excluirTodos(Usuario usuario) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository.findAll();
		Boolean booleano = false;

		if (!listaOcorrencias.isEmpty()) {
			if (usuario.getAcessoCompleto() == true) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					repository.delete(ocorrencia);
					booleano = true;
				}
			} else if (usuario.getAcessoCompleto() == false) {
				for (Ocorrencia ocorrencia : listaOcorrencias) {
					if (ocorrencia.getUsuario().getId() == usuario.getId()) {
						repository.delete(ocorrencia);
						booleano = true;
					}
				}
			}
		}
		return booleano;
	}

	// Metódos Admin (Tratam-se de algumas sobrecargas, como não sei a melhor forma
	// de organiza-los, optei por separar dessa forma)

	// Feito OK
	public boolean listarTodos() {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository.findAll();
		Boolean booleano = false;

		if (!listaOcorrencias.isEmpty()) {
			for (Ocorrencia ocorrencia : listaOcorrencias) {
				System.out.println(ocorrencia);
				booleano = true;
			}
		}
		return booleano;
	}

	// Feito OK
	public boolean listarPeloTipo(TipoOcorrencia tipo) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository
				.findByTipoOcorrencia(tipo.getCode());
		Boolean booleano = false;
		if (!listaOcorrencias.isEmpty()) {
			for (Ocorrencia ocorrencia : listaOcorrencias) {
				System.out.println(ocorrencia.toString());
				booleano = true;
			}
		}
		return booleano;
	}

	// Feito OK
	public boolean atualizarPeloId(int id, String texto) {
		Optional<Ocorrencia> opcional = repository.findById(id);
		if (opcional.isPresent()) {
			Ocorrencia ocorrencia = opcional.get();
			ocorrencia.setId(ocorrencia.getId());
			ocorrencia.setTipoOcorrencia(ocorrencia.getTipoOcorrencia());
			ocorrencia.setUsuario(ocorrencia.getUsuario());
			ocorrencia.setMensagem(texto);
			ocorrencia.setDataHora(LocalDateTime.now());
			repository.save(ocorrencia);
			return true;
		}

		return false;
	}

	// FEITO OK*
	public boolean excluirTodos() {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository.findAll();
		Boolean booleano = false;
		if (!listaOcorrencias.isEmpty()) {
			for (Ocorrencia ocorrencia : listaOcorrencias) {
				repository.delete(ocorrencia);
				booleano = true;
			}
		}
		return booleano;
	}

	// FEITO OK*
	public boolean excluirPeloTipo(TipoOcorrencia tipo) {
		ArrayList<Ocorrencia> listaOcorrencias = (ArrayList<Ocorrencia>) repository
				.findByTipoOcorrencia(tipo.getCode());
		Boolean booleano = false;
		if (!listaOcorrencias.isEmpty()) {
			for (Ocorrencia ocorrencia : listaOcorrencias) {
				repository.delete(ocorrencia);
				booleano = true;
			}
		}
		return booleano;
	}

	public boolean excluirPeloId(int id) {
		Optional<Ocorrencia> opcional = repository.findById(id);
		if (opcional.isPresent()) {
			Ocorrencia ocorrencia = opcional.get();
			repository.delete(ocorrencia);
			return true;
		} else {
			System.out.println("Não foi encontrada nenhuma ocorrência com esse Id");
			return false;
		}
	}

}

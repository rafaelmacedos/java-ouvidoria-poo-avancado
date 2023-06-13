package br.com.ouvidoriamacedo.unifacisa;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ouvidoriamacedo.unifacisa.handler.BusinessException;
import br.com.ouvidoriamacedo.unifacisa.model.Admin;
import br.com.ouvidoriamacedo.unifacisa.model.Pessoa;
import br.com.ouvidoriamacedo.unifacisa.model.Usuario;
import br.com.ouvidoriamacedo.unifacisa.repository.AdminRepository;
import br.com.ouvidoriamacedo.unifacisa.repository.OcorrenciaRepository;
import br.com.ouvidoriamacedo.unifacisa.repository.UsuarioRepository;
import br.com.ouvidoriamacedo.unifacisa.service.AdminService;
import br.com.ouvidoriamacedo.unifacisa.service.OcorrenciaService;
import br.com.ouvidoriamacedo.unifacisa.service.UsuarioService;
import br.com.ouvidoriamacedo.unifacisa.util.LoginAuthentication;
import br.com.ouvidoriamacedo.unifacisa.util.TipoOcorrencia;
import br.com.ouvidoriamacedo.unifacisa.util.View;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	OcorrenciaRepository ocorrenciaRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AdminService adminService;

	@Autowired
	OcorrenciaService ocorrenciaService;

	@Autowired
	LoginAuthentication loginAuthentication;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Rodar a linha abaixo apenas para ter um admin cadastrado no banco
		// adminService.salvar("Diego", "diego@gmail.com", "Senhadiego123");

		Scanner scanner = new Scanner(System.in);
		Boolean menuAutenticacao = true;
		Pessoa pessoaLogada = null;

		View.mostrarBoasVindas();

		while (menuAutenticacao) {
			try {
				// Autenticação do usuário ou admin
				View.mostrarMenuLogin();
				int opcao = Integer.parseInt(scanner.nextLine());

				if (opcao == 1) {
					View.mostrarLogin();
					System.out.println("\nDigite seu e-mail: ");
					String email = scanner.nextLine();

					System.out.println("\nDigite sua senha: ");
					String senha = scanner.nextLine();

					if (loginAuthentication.autenticar(email, senha) == null) {
						throw new BusinessException("\nE-mail ou senha incorretos, tente novamente.");
					} else if (loginAuthentication.autenticar(email, senha).getClass() == Admin.class) {
						pessoaLogada = (Admin) loginAuthentication.autenticar(email, senha);
						System.out.println("\nLogado(a) como " + pessoaLogada.getNome());
						menuAutenticacao = false;
					} else if (loginAuthentication.autenticar(email, senha).getClass() == Usuario.class) {
						pessoaLogada = (Usuario) loginAuthentication.autenticar(email, senha);
						System.out.println("\nLogado(a) como " + pessoaLogada.getNome());
						menuAutenticacao = false;
					}

				}

				else if (opcao == 2) {
					View.mostrarCadastro();
					System.out.println("\nDigite seu nome: ");
					String nome = scanner.nextLine();

					System.out.println("\nDigite seu e-mail: ");
					String email = scanner.nextLine();

					System.out.println("\nA sua Senha de 4-32 caracteres exigindo pelo menos 3 de 4 (maiúsculas"
							+ "\ne letras minúsculas, números e caracteres especiais) e no máximo"
							+ "\n2 caracteres consecutivos iguais.");
					System.out.println("\nDigite sua senha: ");
					String senha = scanner.nextLine();

					if (usuarioService.salvar(nome, email, senha)) {
						System.out.println("Usuário cadastrado com sucesso");
					} else {
						System.out.println("\nNão foi possível realizar o seu cadastro, tente novamente.");
					}

				}

				else if (opcao == 3) {
					break;
				}
			}

			catch (BusinessException e) {
				System.out.println(e.getMessage());
			}

			catch (Exception e) {
				System.out.println("Algo deu errado, tente novamente.");
			}
		}

		while (pessoaLogada != null) {
			try {
				if (pessoaLogada.getClass() == Admin.class) {
					View.mostrarMenuPrincipalAdmin();
					int opcao = Integer.parseInt(scanner.nextLine());

					if (opcao == 1) {
						View.mostrarListarOcorrencia();
						int opcaoListar = Integer.parseInt(scanner.nextLine());

						if (opcaoListar == 1) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.ELOGIO)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 2) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.RECLAMACAO)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 3) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.SUGESTAO)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 4) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.DENUNCIA)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 5) {
							if (!ocorrenciaService.listarTodos()) {
								System.out.println("Não existem ocorrências cadastradas.");
							}
						}

						else if (opcaoListar == 6) {
						}
					}

					if (opcao == 2) {
						View.mostrarAtualizarOcorrencia();
						int opcaoAtualizar = Integer.parseInt(scanner.nextLine());

						if (opcaoAtualizar == 1) {
							if (ocorrenciaService.listarTodos()) {
								System.out.println("\nDigite o ID da ocorrência que você deseja atualizar:");
								int id = Integer.parseInt(scanner.nextLine());

								System.out.println("Digite sua mensagem atualizada: ");
								String mensagem = scanner.nextLine();
								if (!ocorrenciaService.atualizarPeloId(id, mensagem)) {
									System.out.println("Não foi possível encontrar uma ocorrência com esse ID");
								}
							} else {
								System.out.println("Não existem ocorrências cadastradas.");
							}
						}

						else if (opcaoAtualizar == 2) {
						}
					}

					if (opcao == 3) {
						View.mostrarApagarOcorrencia();
						int opcaoApagar = Integer.parseInt(scanner.nextLine());

						if (opcaoApagar == 1) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.ELOGIO)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 2) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.RECLAMACAO)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 3) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.SUGESTAO)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 4) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.DENUNCIA)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 5) {
							if (!ocorrenciaService.excluirTodos()) {
								System.out.println("Não existem ocorrências cadastradas");
							}
						}

						else if (opcaoApagar == 6) {
							ocorrenciaService.listarTodos();
							System.out.println("\nDigite o ID da ocorrência que você deseja apagar");
							int id = Integer.parseInt(scanner.nextLine());
							ocorrenciaService.excluirPeloId(id);
						}

						else if (opcaoApagar == 7) {
						}
					}

					if (opcao == 4) {
						break;
					}
				}

				else if (pessoaLogada.getClass() == Usuario.class) {
					View.mostrarMenuPrincipal();
					int opcao = Integer.parseInt(scanner.nextLine());

					if (opcao == 1) {
						View.mostrarSalvarOcorrencia();
						int opcaoTipo = Integer.parseInt(scanner.nextLine());

						if (opcaoTipo == 1) {
							TipoOcorrencia tipo = TipoOcorrencia.ELOGIO;
							System.out.println("\nDigite sua mensagem: ");
							String mensagem = scanner.nextLine();
							ocorrenciaService.salvar(tipo, mensagem, (Usuario) pessoaLogada);
						}

						else if (opcaoTipo == 2) {
							TipoOcorrencia tipo = TipoOcorrencia.RECLAMACAO;
							System.out.println("\nDigite sua mensagem: ");
							String mensagem = scanner.nextLine();
							ocorrenciaService.salvar(tipo, mensagem, (Usuario) pessoaLogada);
						}

						else if (opcaoTipo == 3) {
							TipoOcorrencia tipo = TipoOcorrencia.SUGESTAO;
							System.out.println("\nDigite sua mensagem: ");
							String mensagem = scanner.nextLine();
							ocorrenciaService.salvar(tipo, mensagem, (Usuario) pessoaLogada);
						}

						else if (opcaoTipo == 4) {
							TipoOcorrencia tipo = TipoOcorrencia.DENUNCIA;
							System.out.println("\nDigite sua mensagem: ");
							String mensagem = scanner.nextLine();
							ocorrenciaService.salvar(tipo, mensagem, (Usuario) pessoaLogada);
						}

						else if (opcaoTipo == 5) {
						}
					}

					if (opcao == 2) {
						View.mostrarListarOcorrencia();
						int opcaoListar = Integer.parseInt(scanner.nextLine());

						if (opcaoListar == 1) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.ELOGIO, (Usuario) pessoaLogada)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 2) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.RECLAMACAO, (Usuario) pessoaLogada)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 3) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.SUGESTAO, (Usuario) pessoaLogada)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 4) {
							if (!ocorrenciaService.listarPeloTipo(TipoOcorrencia.DENUNCIA, (Usuario) pessoaLogada)) {
								System.out.println("Não foi encontrada nenhuma ocorrência desse tipo");
							}
						}

						else if (opcaoListar == 5) {
							if (!ocorrenciaService.listarTodos((Usuario) pessoaLogada)) {
								System.out.println("Não existem ocorrências cadastradas.");
							}
						}

						else if (opcaoListar == 6) {
						}
					}

					if (opcao == 3) {
						View.mostrarAtualizarOcorrencia();
						int opcaoAtualizar = Integer.parseInt(scanner.nextLine());

						if (opcaoAtualizar == 1) {
							if (ocorrenciaService.listarTodos((Usuario) pessoaLogada)) {
								System.out.println("\nDigite o ID da ocorrência que você deseja atualizar:");
								int id = Integer.parseInt(scanner.nextLine());

								System.out.println("Digite sua mensagem atualizada: ");
								String mensagem = scanner.nextLine();
								if (!ocorrenciaService.atualizarPeloId(id, (Usuario) pessoaLogada, mensagem)) {
									System.out.println("Não foi possível encontrar uma ocorrência com esse ID");
								}
							} else {
								System.out.println("Não existem ocorrências cadastradas.");
							}
						}

						else if (opcaoAtualizar == 2) {
						}
					}

					if (opcao == 4) {
						View.mostrarApagarOcorrencia();
						int opcaoApagar = Integer.parseInt(scanner.nextLine());

						if (opcaoApagar == 1) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.ELOGIO, (Usuario) pessoaLogada)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 2) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.RECLAMACAO, (Usuario) pessoaLogada)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 3) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.SUGESTAO, (Usuario) pessoaLogada)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 4) {
							if (!ocorrenciaService.excluirPeloTipo(TipoOcorrencia.DENUNCIA, (Usuario) pessoaLogada)) {
								System.out.println("Não foram encontradas ocorrências desse tipo");
							}
						}

						else if (opcaoApagar == 5) {
							if (!ocorrenciaService.excluirTodos((Usuario) pessoaLogada)) {
								System.out.println("Não existem ocorrências cadastradas");
							}
						}

						else if (opcaoApagar == 6) {
							ocorrenciaService.listarTodos((Usuario) pessoaLogada);
							System.out.println("\nDigite o ID da ocorrência que você deseja apagar");
							int id = Integer.parseInt(scanner.nextLine());
							ocorrenciaService.excluirPeloId(id, (Usuario) pessoaLogada);
						}

						else if (opcaoApagar == 7) {
						}
					}

					if (opcao == 5) {
						break;
					}
				}
			}

			catch (Exception e) {
				System.out.println("Algo deu errado, tente novamente.");
			}

		}
		scanner.close();

	}

}

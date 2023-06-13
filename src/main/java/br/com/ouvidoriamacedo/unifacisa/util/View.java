package br.com.ouvidoriamacedo.unifacisa.util;

public abstract class View {

	public static void mostrarBoasVindas() {
		System.out.println("\n "

				+ "███████████                                      █████   █████  ███                 █████         \r\n"
				+ "░░███░░░░░███                                    ░░███   ░░███  ░░░                 ░░███          \r\n"
				+ " ░███    ░███  ██████  █████████████              ░███    ░███  ████  ████████    ███████   ██████ \r\n"
				+ " ░██████████  ███░░███░░███░░███░░███  ██████████ ░███    ░███ ░░███ ░░███░░███  ███░░███  ███░░███\r\n"
				+ " ░███░░░░░███░███████  ░███ ░███ ░███ ░░░░░░░░░░  ░░███   ███   ░███  ░███ ░███ ░███ ░███ ░███ ░███\r\n"
				+ " ░███    ░███░███░░░   ░███ ░███ ░███              ░░░█████░    ░███  ░███ ░███ ░███ ░███ ░███ ░███\r\n"
				+ " ███████████ ░░██████  █████░███ █████               ░░███      █████ ████ █████░░████████░░██████ \r\n"
				+ "░░░░░░░░░░░   ░░░░░░  ░░░░░ ░░░ ░░░░░                 ░░░      ░░░░░ ░░░░ ░░░░░  ░░░░░░░░  ░░░░░░  \r\n"
				+ "                                                                                                   ");
	}
	
	public static void mostrarMenuLogin() {
		System.out.println("\n\\\\\\\\\\\\\\\\\\\\  Sistema de Ouvidoria do Macedo //////////"
				+ "\n1 - Login"
				+ "\n2 - Cadastro"
				+ "\n3 - Sair"
				+ ""
				+ "\nDigite sua opção: ");
	}
	

	public static void mostrarLogin() {
		System.out.println("\n\\\\\\\\\\ Tela de login //////////");
	}

	public static void mostrarCadastro() {
		System.out.println("\n\\\\\\\\\\ Tela de Cadastro //////////");
	}

	public static void mostrarMenuPrincipalAdmin() {
		System.out.println("\n\\\\\\\\\\ Menu Principal //////////"
				+ "\n1 - Listar ocorrências"
				+ "\n2 - Atualizar ocorrência"
				+ "\n3 - Apagar ocorrências"
				+ "\n4 - Sair"
				+ "" + "\nDigite sua opção: ");
	}
	
	public static void mostrarMenuPrincipal() {
		System.out.println("\n\\\\\\\\\\ Menu Principal //////////"
				+ "\n1 - Cadastrar nova ocorrência"
				+ "\n2 - Listar ocorrências"
				+ "\n3 - Atualizar ocorrência"
				+ "\n4 - Apagar ocorrências"
				+ "\n5 - Sair"
				+ "" + "\nDigite sua opção: ");
	}

	public static void mostrarSalvarOcorrencia() {
		System.out.println("\n\\\\\\\\\\ Cadastrando uma Ocorrência //////////"
				+ "\n1 - Cadastrar Elogio"
				+ "\n2 - Cadastrar Reclamação"
				+ "\n3 - Cadastrar Sugestão"
				+ "\n4 - Cadastrar Denúncia"
				+ "\n5 - Voltar"
				+ ""
				+ "\nDigite sua opção: ");
	}

	public static void mostrarListarOcorrencia() {
		System.out.println("\n\\\\\\\\\\ Listando Ocorrências //////////"
				+ "\n1 - Listar apenas Elogios"
				+ "\n2 - Listar apenas Reclamações"
				+ "\n3 - Listar apenas Sugestões"
				+ "\n4 - Listar apenas Denúncias"
				+ "\n5 - Listar todas as ocorrências"
				+ "\n6 - Voltar"
				+ "" 
				+ "\nDigite sua opção: ");
	}

	public static void mostrarApagarOcorrencia() {
		System.out.println("\n\\\\\\\\\\ Apagando Ocorrências //////////"
				+ "\n1 - Apagar apenas Elogios"
				+ "\n2 - Apagar apenas Reclamações"
				+ "\n3 - Apagar apenas Sugestões"
				+ "\n4 - Apagar apenas Denúncias"
				+ "\n5 - Apagar todas as ocorrências"
				+ "\n6 - Apagar pelo ID"
				+ "\n7 - Voltar"
				+ ""
				+ "\nDigite sua opção: ");
	}

	public static void mostrarAtualizarOcorrencia() {
		System.out.println("\n\\\\\\\\\\ Atualizando Ocorrências //////////"
				+ "\n1 - Atualizar pelo ID"
				+ "\n2 - Voltar"
				+ ""
				+ "\nDigite sua opção: ");
	}

}

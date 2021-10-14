import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.CriticaNaoEncontradaException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Critica;
import modelo.Usuario;
import service.CriticaAppService;
import service.UsuarioAppService;

public class UsuarioPrincipal {
	public static void main(String[] args) {
		String nome;
		String login;
		String senha;
		Usuario usuario;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		UsuarioAppService usuarioAppService = (UsuarioAppService) fabrica.getBean("usuarioAppService");
		CriticaAppService criticaAppService = (CriticaAppService) fabrica.getBean("criticaAppService");

		boolean continua = true;
		while(continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um usuario");
			System.out.println("2. Alterar um usuario");
			System.out.println("3. Remover um usuario");
			System.out.println("4. Listar um usuario e suas criticas");
			System.out.println("5. Listar todos os usuarios e suas criticas");
			System.out.println("6. Sair");
			
			int opcao = Console.readInt("\n" + "Digite um número entre 1 e 5: ");
			
			switch(opcao) {
			case 1:{
				//Cadastro de usuario
				nome = Console.readLine('\n' + "Informe o nome do usuario: ");
				login = Console.readLine('\n' + "Informe o login do usuario: ");
				senha = Console.readLine('\n' + "Informe o senha do usuario: ");
				
				usuario = new Usuario(nome,login,senha, Calendar.getInstance());
				
				long id = usuarioAppService.inclui(usuario);
				
				System.out.println("\n" + "Usuário numero " + id + " incluido com sucesso!");
				
				break;
			}
			case 2:{
				//Aletação de Usuário
				int resposta = Console.readInt('\n' + "Digite o número do usuário que você deseja alterar: ");

				try {
					usuario = usuarioAppService.recuperaUsuario(resposta);
				} catch (UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println(
						"Usuario id=" + usuario.getId() + "\n" +
						" nome=" + usuario.getNome() + 
						" login=" + usuario.getLogin() + 
						" senha=" + usuario.getSenha() + 
						" data de cadastro=" + usuario.getDataCadastroMasc() + 
						" versao=" + usuario.getVersao());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1 - Nome");
				System.out.println('\n' + "2 - Login");
				System.out.println('\n' + "3 - Senha");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 3:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					usuario.setNome(novoNome);

					try {
						usuarioAppService.altera(usuario);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novoLogin = Console.readLine("Digite o novo login: ");

					usuario.setLogin(novoLogin);

					try {
						usuarioAppService.altera(usuario);

						System.out.println('\n' + "Alteração de login efetuada com sucesso!");
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				case 3:
					String novaSenha = Console.readLine("Digite a nova senha: ");

					usuario.setSenha(novaSenha);

					try {
						usuarioAppService.altera(usuario);

						System.out.println('\n' + "Alteração de senha efetuada com sucesso!");
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}
			case 3:{
				//Remover usuário
				int resposta = Console.readInt('\n' + "Digite o número do usuario que você deseja remover: ");

				try {
					usuario = usuarioAppService.recuperaUsuario(resposta);
				} catch (UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				usuario.printaUsuario();

				String resp = Console.readLine('\n' + "Confirma a remoção do usuario?[s/n]");

				if (resp.equals("s")) {
					try {
						usuarioAppService.exclui(usuario);
						System.out.println('\n' + "Usuario removido com sucesso!");
					} catch (UsuarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Usuario não removido.");
				}

				break;
			}
			case 4:{
				//Printa um usuario
				long id = Console.readInt('\n' + "Informe o id do usuario: ");

				try {
					usuario = usuarioAppService.recuperaUsuarioCriticas(id);
				} catch (UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				System.out.println("-----------------------------");
				usuario.printaUsuario();
				List<Critica> criticas = usuario.getCriticas();
				for(Critica c: criticas) {
					Critica critica = null;
					try {
						critica = criticaAppService.recuperaCriticaAutorJogo(c.getId());
					} catch (CriticaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}
					critica.printaCritica();
				}
				System.out.println("-----------------------------");
				break;
			}
			case 5:
				//Printa todos os usuarios
				List<Usuario> usuarios = usuarioAppService.recuperaListaUsuariosCriticas();
				if(usuarios.size() != 0) {
					System.out.println("-----------------------------");
					for(Usuario user: usuarios) {
						user.printaUsuario();
						List<Critica> criticas = user.getCriticas();
						for(Critica c: criticas) {
							Critica critica = null;
							try {
								critica = criticaAppService.recuperaCriticaAutorJogo(c.getId());
							} catch (CriticaNaoEncontradaException e) {
								System.out.println('\n' + e.getMessage());
								break;
							}
							critica.printaCritica();
						}
						System.out.println("-----------------------------");
						
					}
				}else {
					System.out.println('\n' + "Não há usuarios cadastrados com esta descrição.");
				}
				break;
			case 6:
				//Sair
				continua = false;
				break;
			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}
		
	}
}

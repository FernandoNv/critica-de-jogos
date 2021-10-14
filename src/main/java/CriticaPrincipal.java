import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ComentarioNaoEncontradoException;
import excecao.CriticaNaoEncontradaException;
import excecao.JogoNaoEncontradoException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Comentario;
import modelo.Critica;
import modelo.Jogo;
import modelo.Usuario;
import service.ComentarioAppService;
import service.CriticaAppService;
import service.JogoAppService;
import service.UsuarioAppService;

public class CriticaPrincipal {
	public static void main(String[] args) {
		String texto;
		int nota;
		Critica critica;
		Usuario autor;
		Jogo jogo;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		CriticaAppService criticaAppService = (CriticaAppService) fabrica.getBean("criticaAppService");
		UsuarioAppService usuarioAppService = (UsuarioAppService) fabrica.getBean("usuarioAppService");
		JogoAppService jogoAppService = (JogoAppService) fabrica.getBean("jogoAppService");
		ComentarioAppService comentarioAppService = (ComentarioAppService) fabrica.getBean("comentarioAppService");
		
		boolean continua = true;
		while(continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma critica");
			System.out.println("2. Alterar uma critica");
			System.out.println("3. Remover uma critica");
			System.out.println("4. Recupera uma critica");
			System.out.println("5. Listar todas as critica");
			System.out.println("6. Sair");
			
			int opcao = Console.readInt("\n" + "Digite um número entre 1 e 6: ");
			
			switch(opcao) {
			case 1:{
				//Cadastro de critica
				List<Usuario> usuarios = usuarioAppService.recuperaListaUsuariosCriticas();
				if(usuarios.size() != 0) {
					System.out.println("-----------------------------");
					for(Usuario user: usuarios) {
						user.printaUsuario();
						System.out.println("-----------------------------");
						System.out.println("-----------------------------");
						
					}
				}else {
					System.out.println('\n' + "Não há usuarios cadastrados com esta descrição.");
				}
				
				System.out.println("");
				
				List<Jogo> jogos = jogoAppService.recuperaListaJogosECriticas();
				if(jogos.size() != 0) {
					System.out.println("-----------------------------");
					for(Jogo game: jogos) {
						game.printaJogo();
						System.out.println("-----------------------------");
						System.out.println("-----------------------------");
						
					}
				}else {
					System.out.println('\n' + "Não há jogos cadastrados com esta descrição.");
				}
				
				texto = Console.readLine('\n' + "Informe o texto do critica: ");
				nota = Console.readInt('\n' + "Informe a nota do critica: ");
				long idAutor = Console.readInt('\n' + "Informe o id do autor: ");
				
				try {
					autor = usuarioAppService.recuperaUsuario(idAutor);
				}catch(UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				long idJogo = Console.readInt('\n' + "Informe o id do jogo: ");
				
				try {
					jogo = jogoAppService.recuperaJogo(idJogo);
				}catch(JogoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				critica = new Critica(texto, nota, Calendar.getInstance(), autor, jogo);
				
				long idCritica = 0;
				try {
					idCritica = criticaAppService.inclui(critica);
				} catch (UsuarioNaoEncontradoException | JogoNaoEncontradoException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("\n" + "Critica numero " + idCritica + " incluido com sucesso!");
				
				break;
			}
			case 2:{
				//Alteração de Critica
				int resposta = Console.readInt('\n' + "Digite o número da critica que você deseja alterar: ");

				try {
					critica = criticaAppService.recuperaCriticaAutorJogo(resposta);
				} catch (CriticaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				critica.printaCritica();

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1 - Texto");
				System.out.println('\n' + "2 - nota");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de 1 a 2:");

				switch (opcaoAlteracao) {
				case 1:
					String novoTexto = Console.readLine("Digite o novo texto: ");
					critica.setTexto(novoTexto);

					try {
						criticaAppService.altera(critica);
						System.out.println('\n' + "Alteração de texto efetuada com sucesso!");
					} catch (CriticaNaoEncontradaException | UsuarioNaoEncontradoException | JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					int novoNota = Console.readInt("Digite a nova nota: ");

					critica.setNota(novoNota);

					try {
						criticaAppService.altera(critica);
						System.out.println('\n' + "Alteração de texto efetuada com sucesso!");
					} catch (CriticaNaoEncontradaException | UsuarioNaoEncontradoException | JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}
			case 3:{
				//Remover critica
				int resposta = Console.readInt('\n' + "Digite o número da critica que você deseja remover: ");

				try {
					critica = criticaAppService.recuperaCritica(resposta);
				} catch (CriticaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				critica.printaCritica();

				String resp = Console.readLine('\n' + "Confirma a remoção da critica?[s/n]");

				if (resp.equals("s")) {
					try {
						criticaAppService.exclui(critica);
						System.out.println('\n' + "Critica removida com sucesso!");
					} catch (CriticaNaoEncontradaException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Critica não removido.");
				}

				break;
			}
			case 4:{
				//Listar uma critica
				long id = Console.readInt('\n' + "Informe o id da critica: ");
				
				try {
					critica = criticaAppService.recuperaCriticaAutorJogo(id);
				} catch (CriticaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				System.out.println("-----------------------------");
				critica.printaCritica();
				//Printar os comentarios
				List<Comentario> comentarios = critica.getComentarios();
				for(Comentario c: comentarios) {
					Comentario comentario = null;
					try {
						comentario = comentarioAppService.recuperaComentarioAutorCritica(c.getId());
					} catch (ComentarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
						break;
					}
					comentario.printaComentario();
				}
				System.out.println("-----------------------------");
				break;
			}
			case 5:
				//Lista todos as criticas
				List<Critica> criticas = criticaAppService.recuperaListaCriticasAutorJogo();
				if(criticas.size() != 0) {
					System.out.println("-----------------------------");
					for(Critica c: criticas) {
						c.printaCritica();
						System.out.println("-----------------------------");
					}
				}else {
					System.out.println('\n' + "Não há criticas cadastrados com esta descrição.");
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

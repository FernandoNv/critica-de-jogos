import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.ComentarioNaoEncontradoException;
import excecao.CriticaNaoEncontradaException;
import excecao.UsuarioNaoEncontradoException;
import modelo.Comentario;
import modelo.Critica;
import modelo.Usuario;
import service.ComentarioAppService;
import service.CriticaAppService;
import service.UsuarioAppService;

public class ComentarioPrincipal {
	public static void main(String[] args) {
		String texto;
		Usuario autor;
		Critica critica;
		Comentario comentario;
		
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		ComentarioAppService comentarioAppService = (ComentarioAppService) fabrica.getBean("comentarioAppService");
		CriticaAppService criticaAppService = (CriticaAppService) fabrica.getBean("criticaAppService");
		UsuarioAppService usuarioAppService = (UsuarioAppService) fabrica.getBean("usuarioAppService");
		
		boolean continua = true;
		while(continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um comentario");
			System.out.println("2. Alterar um comentario");
			System.out.println("3. Remover um comentario");
			System.out.println("4. Recupera um comentario");
			System.out.println("5. Listar todos os comentarios");
			System.out.println("6. Sair");
			
			int opcao = Console.readInt("\n" + "Digite um número entre 1 e 6: ");
			
			switch(opcao) {
			case 1:{
				//Cadastro de comentario
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
				
				List<Critica> criticas = criticaAppService.recuperaListaCriticasAutorJogo();
				if(criticas.size() != 0) {
					System.out.println("-----------------------------");
					for(Critica c: criticas) {
						c.printaCritica();
						System.out.println("-----------------------------");
						System.out.println("-----------------------------");
						
					}
				}else {
					System.out.println('\n' + "Não há criticas cadastrados com esta descrição.");
				}
				
				long idAutor = Console.readInt('\n' + "Informe o id do autor: ");
				
				try {
					autor = usuarioAppService.recuperaUsuario(idAutor);
				}catch(UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				long idCritica = Console.readInt('\n' + "Informe o id da critica: ");
				
				try {
					critica = criticaAppService.recuperaCriticaAutorJogo(idCritica);
				}catch(CriticaNaoEncontradaException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				
				texto = Console.readLine('\n' + "Informe o texto do comentario: ");
				
				comentario = new Comentario(texto, Calendar.getInstance(), autor, critica);
				
				long idComentario = 0;
				try {
					idComentario = comentarioAppService.inclui(comentario);
				} catch (UsuarioNaoEncontradoException | CriticaNaoEncontradaException e) {
					System.out.println(e.getMessage());
				}
				
				System.out.println("\n" + "Critica numero " + idComentario + " incluido com sucesso!");
				
				break;
			}
			case 2:{
				//Aletação de Comentario
				long resposta = Console.readInt('\n' + "Digite o numero do comentario que você deseja alterar: ");

				try {
					comentario = comentarioAppService.recuperaComentarioAutorCritica(resposta);
				} catch (ComentarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				comentario.printaComentario();

				String novoTexto = Console.readLine("Digite o novo texto: ");
				comentario.setTexto(novoTexto);

				try {
					comentarioAppService.altera(comentario);
					System.out.println('\n' + "Alteração de texto efetuada com sucesso!");
				} catch (ComentarioNaoEncontradoException |CriticaNaoEncontradaException | UsuarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
				}
				
				break;

			}
			case 3:{
				//Remover comentario
				int resposta = Console.readInt('\n' + "Digite o número do comentario que você deseja remover: ");

				try {
					comentario = comentarioAppService.recuperaComentarioAutorCritica(resposta);
				} catch (ComentarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				comentario.printaComentario();

				String resp = Console.readLine('\n' + "Confirma a remoção do comentario?[s/n]");

				if (resp.equals("s")) {
					try {
						comentarioAppService.exclui(comentario);
						System.out.println('\n' + "Comentario removido com sucesso!");
					} catch (ComentarioNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Comentario não removido.");
				}

				break;
			}
			case 4:{
				//Listar um comentario
				long id = Console.readInt('\n' + "Informe o id do comentario: ");
				
				try {
					comentario = comentarioAppService.recuperaComentarioAutorCritica(id);
				} catch (ComentarioNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				System.out.println("-----------------------------");
				comentario.printaComentario();
				System.out.println("-----------------------------");
				System.out.println("-----------------------------");
				break;
			}
			case 5:
				//Lista todos os comentarios
				List<Comentario> comentarios = comentarioAppService.recuperaListaComentariosAutorCritica();
				if(comentarios.size() != 0) {
					System.out.println("-----------------------------");
					for(Comentario c: comentarios) {
						c.printaComentario();
						System.out.println("-----------------------------");
						
					}
				}else {
					System.out.println('\n' + "Não há comentarios cadastrados com esta descrição.");
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

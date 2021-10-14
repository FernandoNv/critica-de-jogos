import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import corejava.Console;
import excecao.CriticaNaoEncontradaException;
import excecao.JogoNaoEncontradoException;
import modelo.Critica;
import modelo.Jogo;
import service.CriticaAppService;
import service.JogoAppService;
import util.Util;

public class JogoPrincipal {
	public static void main(String[] args) {
		String nome;
		String descricao;
		String dataLancamento;
		Jogo jogo;

		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		JogoAppService jogoAppService = (JogoAppService) fabrica.getBean("jogoAppService");
		CriticaAppService criticaAppService = (CriticaAppService) fabrica.getBean("criticaAppService");

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um jogo");
			System.out.println("2. Alterar um jogo");
			System.out.println("3. Remover um jogo");
			System.out.println("4. Listar um jogo e suas criticas");
			System.out.println("5. Listar todos os jogos e suas criticas");
			System.out.println("6. Sair");

			int opcao = Console.readInt("\n" + "Digite um numero entre 1 e 5: ");

			switch (opcao) {
			case 1: {
				// Cadastro de jogo
				nome = Console.readLine('\n' + "Informe o nome do jogo: ");
				descricao = Console.readLine('\n' + "Informe a descricao do jogo: ");
				dataLancamento = Console.readLine("Informe a data de lancamento do jogo: ");
				
				jogo = new Jogo(nome, descricao, Util.strToCalendar(dataLancamento));

				long id = jogoAppService.inclui(jogo);

				System.out.println("\n" + "Jogo id " + id + " incluido com sucesso!");

				break;
			}
			case 2: {
				// Aletação de Jogo
				int resposta = Console.readInt('\n' + "Digite o id do jogo que você deseja alterar: ");

				try {
					jogo = jogoAppService.recuperaJogo(resposta);
				} catch (JogoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				System.out.println("Jogo id=" + jogo.getId() + "\n" + " nome=" + jogo.getNome() + " descricao="
						+ jogo.getDescricao() + " versao="
						+ jogo.getVersao());

				System.out.println('\n' + "O que você deseja alterar?");
				System.out.println('\n' + "1 - Nome");
				System.out.println('\n' + "2 - Descricao");
				System.out.println('\n' + "3 - Data de Lancamento");

				int opcaoAlteracao = Console.readInt('\n' + "Digite um número de de 1 a 3:");

				switch (opcaoAlteracao) {
				case 1:
					String novoNome = Console.readLine("Digite o novo nome: ");
					jogo.setNome(novoNome);

					try {
						jogoAppService.altera(jogo);

						System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
					} catch (JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				case 2:
					String novaDescricao = Console.readLine("Digite a nova descricao: ");

					jogo.setDescricao(novaDescricao);

					try {
						jogoAppService.altera(jogo);

						System.out.println('\n' + "Alteração de descricao efetuada com sucesso!");
					} catch (JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;
					
				case 3:
					String novaDataLancamento = Console.readLine("Digite a nova data de lancamento: ");

					jogo.setDataLancamento(Util.strToCalendar(novaDataLancamento));

					try {
						jogoAppService.altera(jogo);

						System.out.println('\n' + "Alteração de data de lancamento efetuada com sucesso!");
					} catch (JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}

					break;

				default:
					System.out.println('\n' + "Opção inválida!");
				}

				break;
			}
			case 3: {
				// Remover jogo
				int resposta = Console.readInt('\n' + "Digite o id do jogo que você deseja remover: ");

				try {
					jogo = jogoAppService.recuperaJogo(resposta);
				} catch (JogoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}

				jogo.printaJogo();

				String resp = Console.readLine('\n' + "Confirma a remoção do jogo?[s/n]");

				if (resp.equals("s")) {
					try {
						jogoAppService.exclui(jogo);
						System.out.println('\n' + "Jogo removido com sucesso!");
					} catch (JogoNaoEncontradoException e) {
						System.out.println('\n' + e.getMessage());
					}
				} else {
					System.out.println('\n' + "Jogo não removido.");
				}

				break;
			}
			case 4: {
				// Listar jogo e suas criticas
				long id = Console.readInt('\n' + "Informe o id do jogo: ");

				try {
					jogo = jogoAppService.recuperaJogoECriticas(id);
				} catch (JogoNaoEncontradoException e) {
					System.out.println('\n' + e.getMessage());
					break;
				}
				System.out.println("-----------------------------");
				jogo.printaJogo();
				List<Critica> criticas = jogo.getCriticas();
				for(Critica c: criticas) {
//					Critica critica = null;
//					try {
//						critica = criticaAppService.recuperaCriticaAutorJogo(c.getId());
//					} catch (CriticaNaoEncontradaException e) {
//						System.out.println('\n' + e.getMessage());
//						break;
//					}
					System.out.println("Printando Critica");
					System.out.println("Número = " + c.getId() + "\n" +
					         "  Texto = " + c.getTexto() + "\n" +
					         "  Nota = " + c.getNota() + "\n" + 
					         "  Data de Publicacao = " + c.getDataPublicacaoMasc() + "\n" +
					         "  Autor = " + c.getAutor().getNome() + "\n" +
					         "  Jogo = " + jogo.getNome() + "\n" +
					         "  Versao = " + c.getVersao());
					
				}
				System.out.println("-----------------------------");
				break;
			}
			case 5:
				// Listar todos os jogos e suas criticas
				List<Jogo> jogos = jogoAppService.recuperaListaJogosECriticas();
				if(jogos.size() != 0) {
					System.out.println("-----------------------------");
					for(Jogo game: jogos) {
						game.printaJogo();
						List<Critica> criticas = game.getCriticas();
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
					System.out.println('\n' + "Não há jogos cadastrados com esta descrição.");
				}
				
				break;
			case 6:
				// Sair
				continua = false;
				break;
			default:
				System.out.println('\n' + "Opção inválida!");
			}
		}

	}
}

package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import util.Util;


@NamedQueries({
	@NamedQuery(name = "Usuario.recuperaUsuarioCriticas", query = "select u from Usuario u left outer join fetch u.criticas where u.id = ?1"),
	@NamedQuery(name = "Usuario.recuperaListaUsuarios", query = "select u from Usuario u order by u.id"),
	@NamedQuery(name = "Usuario.recuperaListaUsuariosCriticas", query = "select distinct u from Usuario u left outer join fetch u.criticas order by u.id asc"),
})

//o insert e update dinamicos so funcionam na implementação do hibernate
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="usuario")
//@SequenceGenerator(name="SEQUENCIA", 
//sequenceName="SEQ_PRODUTO",
//allocationSize=1)
//Gerador de id dos postgres e oracle
public class Usuario {
	//As anotações podem ser feitas antes dos atributos ou antes dos metodos get
	// 1 - antes do atributos => é usado quando temos validacoes nos metodos set
	// 2 - caso não tenha essas validações, podemos colocar essas anotações nos métodos get
	
	// O campo identificador precisa ser do tipo Class e não tipo primitivo porque na hora de instanciar a classe, esse atributo sera setado como null. 
	// Caso fosse um tipo primitivo, seria setado o seu valor inicial padrão. 
	// Levando a JPA entender que esse elemento intanciado já existe no banco de dados com o identificador com valor padrão do atributo
	
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private Calendar dataCadastro;
	private int versao;
	
	//Um usuario tem varias criticas
	private List<Critica> criticas = new ArrayList<Critica>();
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	//Construtores
	//precisamos de um contrutor vazio pois ele é utilizado pela JPA
	public Usuario() {}
	
	public Usuario(String nome, String login, String senha, Calendar dataCadastro) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	// =================== Métodos get ===================
	@Id //indica que esse campo é um identificador
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica a regra de geração dos identificadores
	@Column(name = "ID") //indica que esse campo existe como coluna no banco de dados e tem o seu nome igual o valor da propriedade name informada
	public Long getId() {
		return id;
	}
	
	@Column(name = "NOME")
	public String getNome() {
		return nome;
	}
	
	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	@Column(name = "SENHA")
	public String getSenha() {
		return senha;
	}
	
	@Column(name = "DATA_CADASTRO")
	@Temporal(TemporalType.DATE)
	public Calendar getDataCadastro() {
		return dataCadastro;
	}
	
	@Transient 
	// serve para indicar que esse campo nao existe como coluna dentro do banco de dados. 
	//Nesse caso ele é apenas uma mascara para formatar a data DD/MM/AAAA
	public String getDataCadastroMasc() {
		return Util.calendarToStr(dataCadastro);
	}
	
	@Version
	public int getVersao() {
		return versao;
	}
	
	@OneToMany(mappedBy = "autor")
	@OrderBy
	/*
	 * Com o atributo mappedBy. Sem ele a JPA irá procurar pela tabela
	 * PRODUTO_LANCE. Com ele, ao se tentar recuperar um produto e todos os seus
	 * lances, o join de PRODUTO e LANCE irá acontecer através da chave estrangeira
	 * existente em LANCE.
	 */
	public List<Critica> getCriticas() {
		return criticas;
	}
	
	@OneToMany(mappedBy="autor")
	@OrderBy
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	// =================== Métodos set ===================
	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}
	

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void printaUsuario() {
		System.out.println("Printando Usuário");
		System.out.println("Número = " + id + "\n" +
		         "  Nome = " + nome + "\n" +
		         "  Login = " + login + "\n" + 
		         "  Senha = " + senha + "\n" +
		         "  Data de cadastro = " + getDataCadastroMasc() + "\n" +
		         "  Versao = " + versao);
	}
	
	public static void printaListaUsuarios(List<Usuario> usuarios) {
		for(Usuario usuario: usuarios) {
			usuario.printaUsuario();
		}
	}
	
}

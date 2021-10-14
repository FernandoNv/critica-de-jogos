package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@NamedQuery(name = "Critica.recuperaCriticaAutorJogo", query = "select c from Critica c left outer join fetch c.autor left outer join fetch c.jogo left outer join fetch c.comentarios where c.id = ?1"),
	@NamedQuery(name = "Critica.recuperaListaCriticas", query = "select c from Critica c order by c.id"),
	@NamedQuery(name = "Critica.recuperaListaCriticasAutorJogo", query = "select distinct c from Critica c left outer join fetch c.autor left outer join fetch c.jogo order by c.id asc"),
})

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="CRITICA")
public class Critica {
	private Long id;
	private String texto;
	private int nota;
	private Calendar dataPublicacao;
	//Uma critica se refere a um unico usuario e jogo
	private Usuario autor;
	private Jogo jogo;
	private int versao;
	
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Critica() {}
	
	public Critica(String texto, int nota, Calendar dataPublicacao, Usuario autor, Jogo jogo) {
		super();
		this.texto = texto;
		this.nota = nota;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
		this.jogo = jogo;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	@Column(name="TEXTO")
	public String getTexto() {
		return texto;
	}
	
	@Column(name="NOTA")
	public int getNota() {
		return nota;
	}
	
	@Column(name="DATA_PUBLICACAO")
	@Temporal(TemporalType.DATE)
	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}
	
	@Transient
	public String getDataPublicacaoMasc() {
		return Util.calendarToStr(dataPublicacao);
	}
	
	@Version
	public int getVersao() {
		return versao;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTOR_ID") // nome do atributo no bd q serve como fk de usuario
	public Usuario getAutor() {
		return autor;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="JOGO_ID")
	public Jogo getJogo() {
		return jogo;
	}
	
	
	@OneToMany(mappedBy = "critica")
	@OrderBy
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public void printaCritica() {
		System.out.println("Printando Critica");
		System.out.println("Número = " + getId() + "\n" +
		         "  Texto = " + getTexto() + "\n" +
		         "  Nota = " + getNota() + "\n" + 
		         "  Data de Publicacao = " + getDataPublicacaoMasc() + "\n" +
		         "  Autor = " + autor.getNome() + "\n" +
		         "  Jogo = " + jogo.getNome() + "\n" +
		         "  Versao = " + getVersao());
		
	}

	public static void printaListaCriticas(List<Critica> criticas) {
		for(Critica critica: criticas) {
			critica.printaCritica();
		}
	}
}

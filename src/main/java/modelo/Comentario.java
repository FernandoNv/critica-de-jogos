package modelo;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import util.Util;

@NamedQueries({
	@NamedQuery(name = "Comentario.recuperaComentarioAutorCritica", query = "select c from Comentario c left outer join fetch c.autor left outer join fetch c.critica where c.id = ?1"),
	@NamedQuery(name = "Comentario.recuperaListaComentarios", query = "select c from Comentario c order by c.id"),
	@NamedQuery(name = "Comentario.recuperaListaComentariosAutorCritica", query = "select distinct c from Comentario c left outer join fetch c.autor left outer join fetch c.critica order by c.id asc"),
})


@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="COMENTARIO")
public class Comentario {
	private Long id;
	private String texto;
	private Calendar dataPublicacao;
	private Usuario autor;
	private Critica critica;
	private int versao;
	
	public Comentario() {}
	
	public Comentario(String texto, Calendar dataPublicacao, Usuario autor, Critica critica) {
		super();
		this.texto = texto;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
		this.critica = critica;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	
	@Column(name="TEXTO")
	public String getTexto() {
		return texto;
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTOR_ID")
	public Usuario getAutor() {
		return autor;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CRITICA_ID")
	public Critica getCritica() {
		return critica;
	}
	
	@Version
	public int getVersao() {
		return versao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setCritica(Critica critica) {
		this.critica = critica;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	public void printaComentario() {
		System.out.println("Printando Comentário");
		System.out.println("Número = " + this.getId() + "\n" +
		         "  Texto = " + this.getTexto() + "\n" +
		         "  Data de Publicacao = " + this.getDataPublicacaoMasc() + "\n" +
		         "  Autor = " + this.autor.getNome() + "\n" +
		         "  Critica id = " + this.critica.getId() + "\n" +
		         "  Versao = " + this.getVersao());
	}
	
	public static void printaComentarios(List<Comentario> comentarios) {
		for(Comentario comentario : comentarios) {
			comentario.printaComentario();
		}
	}
	
	
}

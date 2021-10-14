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
	@NamedQuery(name = "Jogo.recuperaJogoECriticas", query = "select j from Jogo j left outer join fetch j.criticas c left outer join fetch c.autor where j.id = ?1"),
	@NamedQuery(name = "Jogo.recuperaListaJogos", query = "select j from Jogo j order by j.id"),
	@NamedQuery(name = "Jogo.recuperaListaJogosECriticas", query = "select distinct j from Jogo j left outer join fetch j.criticas order by j.id asc"),
})
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="jogo")
public class Jogo {
	private Long id;
	private String nome;
	private String descricao;
	private Calendar dataLancamento;
	private int versao;
	
	private List<Critica> criticas = new ArrayList<Critica>();
	
	public Jogo() {}
	
	public Jogo(String nome, String descricao, Calendar dataLancamento) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.dataLancamento = dataLancamento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Column(name = "DATA_LANCAMENTO")
	@Temporal(TemporalType.DATE)
	public Calendar getDataLancamento() {
		return dataLancamento;
	}
	
	@Transient
	public String getDataLancamentoMasc() {
		return Util.calendarToStr(dataLancamento);
	}
	
	@OneToMany(mappedBy="jogo")
	@OrderBy
	public List<Critica> getCriticas() {
		return criticas;
	}

	@Version
	public int getVersao() {
		return versao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public void setVersao(int versao) {
		this.versao = versao;
	}
	
	
	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}
	
	public void printaJogo() {
		System.out.println("Printando Jogo");
		System.out.println("Número = " + getId() + "\n" +
				         "  Nome = " + getNome() + "\n" +
				         "  Descricao = " + getDescricao() + "\n" + 
				         "  Data de Lancamento = " + getDataLancamentoMasc() + "\n" +
				         "  Versao = " + getVersao());
	}

	public static void printaListaJogos(List<Jogo> jogos) {
		for (Jogo jogo: jogos) {
			jogo.printaJogo();
		}
	}
	
	
}

package com.example.Critica.de.Jogos.model;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Critica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCritica;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario autor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idJogo")
    private Jogo jogo;

    @NotNull
    private Date dataCriacao;

    @NotNull
    private String textoCritica;

    public String getTextoCritica() {
        return textoCritica;
    }

    public void setTextoCritica(String textoCritica) {
        this.textoCritica = textoCritica;
    }

    public Integer getidCritica() {
        return idCritica;
    }

    public void setidCritica(Integer idCritica) {
        this.idCritica = idCritica;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}

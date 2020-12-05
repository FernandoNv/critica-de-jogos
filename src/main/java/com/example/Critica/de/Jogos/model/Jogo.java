package com.example.Critica.de.Jogos.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idJogo;

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private String genero;

    @NotNull
    private int anoLancamento;

    @OneToMany(targetEntity=Critica.class, mappedBy = "jogo")
    private List<Critica> criticaList = new ArrayList<>();

    public Integer getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(Integer idJogo) {
        this.idJogo = idJogo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public List<Critica> getCriticaList() {
        return criticaList;
    }
}
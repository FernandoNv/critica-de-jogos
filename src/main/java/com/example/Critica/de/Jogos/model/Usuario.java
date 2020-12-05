package com.example.Critica.de.Jogos.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @OneToMany(targetEntity=Critica.class, mappedBy = "autor")
    private List<Critica> criticaList = new ArrayList<>();

    public void addCritica(Critica critica){
        if(critica != null){
            this.criticaList.add(critica);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public List<Critica> getCriticaList() {
        return criticaList;
    }

}

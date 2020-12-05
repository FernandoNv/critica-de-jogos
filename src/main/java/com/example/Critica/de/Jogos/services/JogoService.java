package com.example.Critica.de.Jogos.services;

import com.example.Critica.de.Jogos.model.Jogo;
import com.example.Critica.de.Jogos.model.Usuario;
import com.example.Critica.de.Jogos.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository repository;

    public Jogo cadastrarjogo(Jogo jogo){
        return this.repository.save(jogo);
    }

    public Jogo alteraJogo(Jogo jogo){
        if(jogo.getIdJogo() == null) return null;
        return this.repository.save(jogo);
    }

    public Jogo buscarjogoById(Integer idJogo){
        Optional<Jogo> jogo = this.repository.findById(idJogo);
        if(jogo.isEmpty()) return null;

        return jogo.get();
    }

    public boolean excluirJogo(Integer idJogo){
        Optional<Jogo> jogo = this.repository.findById(idJogo);

        if(jogo.isEmpty()) return false;

        this.repository.delete(jogo.get());
        //Tratar os elementos com alguma dependecia com ele

        return true;
    }

    public List<Jogo> buscarTodos(){
        List<Jogo> jogo = this.repository.findAll();

        if(jogo.isEmpty()) return null;

        return jogo;
    }

    public JogoRepository getRepository() {
        return repository;
    }
}

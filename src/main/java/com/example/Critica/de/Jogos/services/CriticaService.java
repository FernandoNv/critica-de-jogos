package com.example.Critica.de.Jogos.services;

import com.example.Critica.de.Jogos.model.Critica;
import com.example.Critica.de.Jogos.model.Usuario;
import com.example.Critica.de.Jogos.repository.CriticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CriticaService {

    @Autowired
    private CriticaRepository repository;
    @Autowired
    private UsuarioService usuarioService;

    public CriticaRepository getRepository() {
        return repository;
    }

//    public Critica cadastrarCritica(Critica critica){
//        Usuario autor = usuarioService.buscarUsuarioById(critica);
//
//        return this.repository.save(critica);
//    }
}

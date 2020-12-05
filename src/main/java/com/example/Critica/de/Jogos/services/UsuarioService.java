package com.example.Critica.de.Jogos.services;

import com.example.Critica.de.Jogos.model.Critica;
import com.example.Critica.de.Jogos.model.Jogo;
import com.example.Critica.de.Jogos.model.Usuario;
import com.example.Critica.de.Jogos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioRepository getRepository() {
        return repository;
    }

    public Usuario cadastrarUsuario(Usuario usuario){
        return this.repository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario usuario){
        if(usuario.getIdUsuario() == null) return null;

        return this.repository.save(usuario);
    }

    public boolean excluirUsuario(Integer idUsuario){
        Optional<Usuario> usuario = this.repository.findById(idUsuario);

        if(usuario.isEmpty()) return false;

        this.repository.delete(usuario.get());

        return true;
    }

    public Usuario buscarUsuarioById(Integer idUsuario){
        Optional<Usuario> usuario = this.repository.findById(idUsuario);
        if(usuario.isEmpty()) return null;
        return usuario.get();
    }

    public Usuario addCritica(Critica critica, Integer idUsuario){
        Optional<Usuario> usuarioEncontrado = this.repository.findById(idUsuario);
        if(usuarioEncontrado.isEmpty()) return null;
        Usuario usuario = usuarioEncontrado.get();
        usuario.addCritica(critica);

        return this.alterarUsuario(usuario);
    }

    public List<Usuario> buscarTodos(){
        List<Usuario> usuarios = this.repository.findAll();

        if(usuarios.isEmpty()) return null;

        return usuarios;
    }
}

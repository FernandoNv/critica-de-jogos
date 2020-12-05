package com.example.Critica.de.Jogos.resources;

import com.example.Critica.de.Jogos.model.Critica;
import com.example.Critica.de.Jogos.model.Jogo;
import com.example.Critica.de.Jogos.model.Usuario;
import com.example.Critica.de.Jogos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        usuario = this.service.cadastrarUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping
    public ResponseEntity<?> alterarUsuario(@RequestBody Usuario usuario){
        usuario = this.service.alterarUsuario(usuario);
        if(usuario == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/{idUsuario}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer idUsuario){
        boolean confirmacao = this.service.excluirUsuario(idUsuario);

        if(!confirmacao) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioById(@PathVariable Integer idUsuario){
        Usuario usuario = this.service.buscarUsuarioById(idUsuario);
        if(usuario == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping(path = "/{idUsuario}")
    public ResponseEntity<?> addCritica(@RequestBody Critica critica, @PathVariable Integer idUsuario){
        Usuario usuario = this.service.addCritica(critica, idUsuario);
        if(usuario == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscaTodos(){
        List<Usuario> usuarios =this.service.buscarTodos();
        if(usuarios == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
}

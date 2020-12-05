package com.example.Critica.de.Jogos.resources;

import com.example.Critica.de.Jogos.model.Jogo;
import com.example.Critica.de.Jogos.services.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoResource {
    @Autowired
    private JogoService service;

    @PostMapping
    public ResponseEntity<Jogo> cadastrarJogo(@RequestBody Jogo jogo){
        jogo = this.service.cadastrarjogo(jogo);

        return ResponseEntity.status(HttpStatus.CREATED).body(jogo);
    }

    @PutMapping
    public ResponseEntity<Jogo> alteraJogo(@RequestBody Jogo jogo){
        jogo = this.service.alteraJogo(jogo);
        if(jogo == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(jogo);
    }

    @GetMapping(path = "/{idJogo}")
    public ResponseEntity<Jogo> buscaJogo(@PathVariable Integer idJogo){
        Jogo jogo = this.service.buscarjogoById(idJogo);
        if(jogo == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(jogo);
    }

    @DeleteMapping(path = "/{idJogo}")
    public ResponseEntity<?> excluiJogo(@PathVariable Integer idJogo){
        boolean confirmacao = this.service.excluirJogo(idJogo);
        if(!confirmacao) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> buscaTodos(){
        List<Jogo> jogos =this.service.buscarTodos();
        if(jogos == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(jogos);
    }
}

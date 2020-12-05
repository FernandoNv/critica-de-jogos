package com.example.Critica.de.Jogos.resources;
import com.example.Critica.de.Jogos.services.CriticaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/criticas")
public class CriticaResourse {

    private CriticaService service;

//    @PostMapping
//    public ResponseEntity<Critica> cadastrarCritica(@RequestBody Critica critica){
//        critica = this.service.cadastrarCritica(critica);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(critica);
//    }
}

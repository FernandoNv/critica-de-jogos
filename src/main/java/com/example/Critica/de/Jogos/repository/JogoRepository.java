package com.example.Critica.de.Jogos.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Critica.de.Jogos.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer> {

}

package com.example.Critica.de.Jogos.repository;

import com.example.Critica.de.Jogos.model.Critica;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CriticaRepository extends JpaRepository<Critica, Integer>{
}

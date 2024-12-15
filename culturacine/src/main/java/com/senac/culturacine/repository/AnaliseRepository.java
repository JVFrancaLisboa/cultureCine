package com.senac.culturacine.repository;

import com.senac.culturacine.entities.AnaliseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnaliseRepository extends JpaRepository<AnaliseEntity, Long> {

    @Query(value = "SELECT * FROM analises a WHERE a.filme_id = :id", nativeQuery = true)
    List<AnaliseEntity> listarAnalisesPorFilme(Long id);
}
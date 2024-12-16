package com.senac.culturacine.repository;

import com.senac.culturacine.entities.AnaliseEntity;
import com.senac.culturacine.entities.FilmeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;

    @Test
    void TestarSalvarFilme(){
        List<AnaliseEntity> analises = new ArrayList<>();
        FilmeEntity filme = new FilmeEntity();
        filme.setTitulo("Matrix");
        filme.setSinopse("Um clássico...");
        filme.setAno("2000");
        filme.setGenero("Suspense");
        filme.setAnalises(analises);

        FilmeEntity salvo = filmeRepository.save(filme);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getTitulo()).isEqualTo("Matrix");
    }
}
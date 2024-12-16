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
        FilmeEntity filme = new FilmeEntity();
        filme.setTitulo("Matrix");
        filme.setSinopse("Um clássico...");
        filme.setAno("2000");
        filme.setGenero("Suspense");
        filme.setAnalises(getAnalises());

        FilmeEntity salvo = filmeRepository.save(filme);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getTitulo()).isEqualTo("Matrix");
        assertThat(salvo.getSinopse()).isEqualTo("Um clássico...");
        assertThat(salvo.getAno()).isEqualTo("2000");
        assertThat(salvo.getGenero()).isEqualTo("Suspense");
        assertThat(salvo.getAnalises()).hasSize(2);
    }

    private List<AnaliseEntity> getAnalises(){
        List<AnaliseEntity> analises = new ArrayList<>();
        analises.add(getAnalise("Ótimo Filme! Me prendeu!", "5"));
        analises.add(getAnalise("Surpreendente!", "4"));
        return analises;
    }

    private AnaliseEntity getAnalise(String analiseAtributo, String nota){
        AnaliseEntity analise = new AnaliseEntity();
        analise.setAnalise(analiseAtributo);
        analise.setNota(nota);
        return analise;
    }
}
package com.senac.culturacine.repository;

import com.senac.culturacine.entities.AnaliseEntity;
import com.senac.culturacine.entities.FilmeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Transactional
@ActiveProfiles("test")
class FilmeRepositoryTest {

    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private AnaliseRepository analiseRepository;

    @Test
    @DisplayName("Check the saved movie data")
    void TestarSalvarFilme(){
        FilmeEntity salvo = filmeRepository.save(getFilme(
                "Matrix", "A classic...", "2000", "Suspense"));

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getTitulo()).isEqualTo("Matrix");
        assertThat(salvo.getSinopse()).isEqualTo("A classic...");
        assertThat(salvo.getAno()).isEqualTo("2000");
        assertThat(salvo.getGenero()).isEqualTo("Suspense");
        assertThat(salvo.getAnalises()).hasSize(2);
    }

    @Test
    @DisplayName("Testing search for movie by ID")
    void TestarProcurarFilmePorId(){
        FilmeEntity salvo = filmeRepository.save(getFilme(
                "the nun", "is a 2018 horror film", "2018", "horror"));
        Optional<FilmeEntity> find = filmeRepository.findById(salvo.getId());
        assertThat(find).isNotEmpty();
    }

    @Test
    @DisplayName("Check the saved analysis data")
    void testarSalvarAnalise(){
        AnaliseEntity salvo = analiseRepository.save(getAnalise("Very good", "5"));

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getAnalise()).isEqualTo("Very good");
        assertThat(salvo.getNota()).isEqualTo("5");
    }

    @Test
    @DisplayName("Testing search for analysis by ID")
    void TestarProcurarAnalisePorId(){
        AnaliseEntity salvo = analiseRepository.save(getAnalise("Very good", "4"));
        Optional<AnaliseEntity> find = analiseRepository.findById(salvo.getId());
        assertThat(find).isNotEmpty();
    }

    private List<AnaliseEntity> getAnalises(){
        List<AnaliseEntity> analises = new ArrayList<>();
        analises.add(getAnalise("Great movie, it surprised me!", "5"));
        analises.add(getAnalise("Surprising!", "4"));
        return analises;
    }

    private AnaliseEntity getAnalise(String analiseAtributo, String nota){
        AnaliseEntity analise = new AnaliseEntity();
        analise.setAnalise(analiseAtributo);
        analise.setNota(nota);
        return analise;
    }

    private FilmeEntity getFilme(String titulo, String sinopse, String ano, String genero){
        FilmeEntity filme = new FilmeEntity();
        filme.setTitulo(titulo);
        filme.setSinopse(sinopse);
        filme.setAno(ano);
        filme.setGenero(genero);
        filme.setAnalises(getAnalises());
        return filme;
    }
}
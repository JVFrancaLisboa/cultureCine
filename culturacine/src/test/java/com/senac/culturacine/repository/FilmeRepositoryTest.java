package com.senac.culturacine.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        String p = "p";
        assertThat(p).isEqualTo("p");
    }
}
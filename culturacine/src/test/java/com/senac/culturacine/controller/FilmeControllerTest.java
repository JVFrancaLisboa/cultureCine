package com.senac.culturacine.controller;

import com.jayway.jsonpath.JsonPath;
import com.senac.culturacine.entities.AnaliseEntity;
import com.senac.culturacine.entities.FilmeEntity;
import com.senac.culturacine.service.FilmeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmeController.class)
class FilmeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmeService filmeService;

    List<AnaliseEntity> analysis = new ArrayList<>();

    @Test
    void listarTodosOsFilmes() throws Exception {
        FilmeEntity filme1 = new FilmeEntity(
                "Matrix", "Matrix...", "suspense", "2000", analysis);
        FilmeEntity filme2 = new FilmeEntity("Hulk", "Hulk is...", "action", "2005", analysis);

        List<FilmeEntity> filmesMock = Arrays.asList(filme1, filme2);

        Mockito.when(filmeService.listarFilmes()).thenReturn(filmesMock);

        mockMvc.perform(get("/filmes/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].titulo", is("Matrix")))
                .andExpect(jsonPath("$[1].titulo", is("Hulk")));
    }

    @Test
    void procurarPorId() {
    }

    @Test
    void atualizarFilme() {
    }

    @Test
    void adicionar() {
    }

    @Test
    void deletarFilme() {
    }
}
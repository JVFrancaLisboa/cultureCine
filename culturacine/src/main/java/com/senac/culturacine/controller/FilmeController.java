package com.senac.culturacine.controller;

import com.senac.culturacine.entities.FilmeEntity;
import com.senac.culturacine.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeSevice;

    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> listarTodosOsFilmes(){
        List<FilmeEntity> filmes = filmeSevice.listarFilmes();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> procurarPorId(@PathVariable Long id){
        FilmeEntity filme = filmeSevice.getFilmeId(id);
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Long id, @RequestBody FilmeEntity filme){
        var filmeAtualizado = filmeSevice.atualizarFilme(id, filme);
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> adicionar(@RequestBody FilmeEntity filme){
        var filmeCriado = filmeSevice.criarfilme(filme);
        return new ResponseEntity<>(filmeCriado, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        filmeSevice.deletarFilme(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
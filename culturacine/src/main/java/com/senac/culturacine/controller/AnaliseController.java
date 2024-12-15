package com.senac.culturacine.controller;

import com.senac.culturacine.entities.AnaliseEntity;
import com.senac.culturacine.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes/{filmeId}/analises")
public class AnaliseController {
    @Autowired
    AnaliseService analiseService;

    @GetMapping
    public ResponseEntity<List<AnaliseEntity>> listarAnalisesPorFilme(@PathVariable("filmeId") Long filmeId){
        List<AnaliseEntity> analises = analiseService.listarAnalisesPorFilme(filmeId);
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnaliseEntity> adicionarAnalise(@PathVariable("filmeId")Long filmeId, @RequestBody AnaliseEntity analise){
        var newAnalise = analiseService.criarAnalise(filmeId, analise);
        return new ResponseEntity<>(newAnalise, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnaliseEntity> atualizarAnalise(@PathVariable("id") Long id, @RequestBody AnaliseEntity analise){
        AnaliseEntity analiseATT = analiseService.atualizarAnalise(id, analise);
        return new ResponseEntity<>(analiseATT, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable("id") Long id){
        analiseService.deletarAnalise(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
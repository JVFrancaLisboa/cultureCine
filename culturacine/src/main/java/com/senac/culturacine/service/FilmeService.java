package com.senac.culturacine.service;

import com.senac.culturacine.entities.FilmeEntity;
import com.senac.culturacine.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public List<FilmeEntity> listarFilmes(){
        return filmeRepository.findAll();
    }

    public FilmeEntity getFilmeId(Long id){
        return filmeRepository.findById(id).orElse(null);
    }

    public FilmeEntity atualizarFilme(Long id, FilmeEntity filmeAtualizado){
        FilmeEntity filme = getFilmeId(id);

        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setSinopse(filmeAtualizado.getSinopse());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAno(filmeAtualizado.getAno());

        filmeRepository.save(filme);
        return filme;
    }

    public FilmeEntity criarfilme(FilmeEntity filme){
        filme.setId(null);
        filmeRepository.save(filme);
        return filme;
    }

    public void deletarFilme(Long id){
        FilmeEntity filme = getFilmeId(id);
        filmeRepository.deleteById(filme.getId());
    }
}

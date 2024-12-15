package com.senac.culturacine.service;

import com.senac.culturacine.entities.AnaliseEntity;
import com.senac.culturacine.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {
    @Autowired
    AnaliseRepository analiseRepository;
    @Autowired
    FilmeService filmeSevice;

    public List<AnaliseEntity> listarAnalisesPorFilme(Long id){
        return analiseRepository.listarAnalisesPorFilme(id);
    }

    public AnaliseEntity criarAnalise(Long filmeId, AnaliseEntity analise){
        analise.setId(null);
        analise.setFilme(filmeSevice.getFilmeId(filmeId));
        analiseRepository.save(analise);
        return analise;
    }

    public AnaliseEntity atualizarAnalise(Long id, AnaliseEntity analiseAtualizada){
        AnaliseEntity analise = getAnaliseId(id);
        analise.setAnalise(analiseAtualizada.getAnalise());
        analise.setNota(analiseAtualizada.getNota());
        analiseRepository.save(analise);
        return analise;
    }

    public AnaliseEntity getAnaliseId(Long id){
        return analiseRepository.findById(id).orElse(null);
    }

    public void deletarAnalise(Long id){
        AnaliseEntity analise = getAnaliseId(id);
        analiseRepository.deleteById(analise.getId());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.culturacine.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 *
 * @author josue
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "filmes")
public class FilmeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String sinopse;
    private String genero;
    private String ano;
    // Mapeamento 1:N, um filme tem várias análises
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<AnaliseEntity> analises;

    public FilmeEntity(String titulo, String sinopse, String genero, String ano, List<AnaliseEntity> analises) {
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.genero = genero;
        this.ano = ano;
        this.analises = analises;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.senac.culturacine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 *
 * @author josue
 */
@Data
@Entity
@Table(name = "analises")
public class AnaliseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String analise;
    private String nota;
    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonIgnore  // Evita a serialização do filme dentro da análise
    private FilmeEntity filme;
}
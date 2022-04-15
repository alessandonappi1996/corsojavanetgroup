package com.example.europcar.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "HB_INVESTIMENTO")

public class Investimento {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome_investimento")
    private String nome_investimento;

    @Column(name = "totale_investimento")
    private String nome;

    @Column(name = "data_investimento")
    private LocalDate data_investimento;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "categoria")
    private Categoria categoria;

}



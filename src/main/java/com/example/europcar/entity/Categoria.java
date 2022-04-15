package com.example.europcar.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "HB_CATEGORIA")


public class Categoria {

        @Column(name = "id")
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;


        @Column(name = "nome_categoria")
        private String nome_categoria;
    }



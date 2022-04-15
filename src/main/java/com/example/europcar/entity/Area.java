package com.example.europcar.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "HB_AREA")
public class Area {

        @Column
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @Column(name = "nome")
        private String nome;

        @Column(name = "investimenti")
        private int investimenti;

}


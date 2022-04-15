package com.example.europcar.service;

import com.example.europcar.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria findById(Integer id);

    List<Categoria> findAll();

    Categoria delete(Integer id);

    Categoria save(Categoria categoria);
}



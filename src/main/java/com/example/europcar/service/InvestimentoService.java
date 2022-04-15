package com.example.europcar.service;

import com.example.europcar.entity.Investimento;

import java.util.List;
import java.util.Set;

public interface InvestimentoService {
    Investimento findById(Integer id);

    List<Investimento> findAll();

    Investimento delete(Integer id);

    Investimento save(Investimento investimento);



}

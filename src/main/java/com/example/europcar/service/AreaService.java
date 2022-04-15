package com.example.europcar.service;

import com.example.europcar.entity.Area;

import java.util.List;

public interface AreaService {

    Area findById(Integer id);

    List<Area> findAll();

    Area delete(Integer id);

    Area save(Area area);
}


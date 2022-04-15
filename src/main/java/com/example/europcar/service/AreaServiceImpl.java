package com.example.europcar.service;

import com.example.europcar.entity.Area;
import com.example.europcar.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService  {
    @Autowired
    AreaRepository areaRepository;

    @Override
    public Area findById(Integer id) {
        Area area = areaRepository.findById(id).get();
        return area;
    }

    @Override
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    public Area delete(Integer id) {
        Area area = areaRepository.findById(id).get();
        areaRepository.delete(area);
        return area;
    }

    @Override
    public Area save(Area area) {
        return areaRepository.save(area);
    }
}

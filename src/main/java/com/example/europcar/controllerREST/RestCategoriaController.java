package com.example.europcar.controllerREST;

import com.example.europcar.entity.Categoria;
import com.example.europcar.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")

public class RestCategoriaController {

    @Autowired
    CategoriaService categoriaService;
}
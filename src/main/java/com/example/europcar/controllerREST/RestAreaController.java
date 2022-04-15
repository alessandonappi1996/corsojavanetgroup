package com.example.europcar.controllerREST;

import com.example.europcar.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/area")

public class RestAreaController {
    @Autowired
    AreaService areaService;
}

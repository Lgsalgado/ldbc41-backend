package com.example.ldbc41.controllers;


import com.example.ldbc41.models.Pais;
import com.example.ldbc41.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping("/paises")
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }
}


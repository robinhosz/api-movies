package br.com.movieproject.controller;

import br.com.movieproject.dto.FormDTO;
import br.com.movieproject.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "api/v2/form")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FormController {

    @Autowired
    private FormService service;

    @PostMapping
    public ResponseEntity<FormDTO> create(@RequestBody FormDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}

package br.com.movieproject.controller;

import br.com.movieproject.dto.FormDTO;
import br.com.movieproject.service.FormService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v2/form")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FormController {

    @Autowired
    private FormService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<FormDTO> create(@RequestBody FormDTO obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @GetMapping("/dados")
    public ResponseEntity<List<FormDTO>> findAll() {
        List<FormDTO> listDTO = service.findAll().stream().map(x -> mapper.map(x, FormDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}

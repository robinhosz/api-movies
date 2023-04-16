package br.com.movieproject.service;

import br.com.movieproject.dto.FormDTO;
import br.com.movieproject.model.Form;
import br.com.movieproject.repository.FormRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    @Autowired
    private FormRepository repository;

    @Autowired
    private ModelMapper mapper;

    public Form create(FormDTO obj) {

        return repository.save(mapper.map(obj, Form.class));
    }

    public List<Form> findAll() {

        return repository.findAll();
    }
}

package com.br.personal.account.service;

import com.br.personal.account.api.dto.PersonDto;
import com.br.personal.account.model.PersonEntity;
import com.br.personal.account.repository.PersonRepository;
import com.fasterxml.jackson.databind.cfg.MapperBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;
    public List<PersonDto> findAll() {
        return repository.findAll().stream().map(e -> new ModelMapper().map(e, PersonDto.class)).collect(Collectors.toList());
    }

    public PersonEntity create(PersonDto personDto){

        PersonEntity personEntity = new ModelMapper().map(personDto, PersonEntity.class);

        repository.save(personEntity);

        return personEntity;
    }
}

package com.example.adauto.testebatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.adauto.testebatch.entity.dto.PersonRequestDTO;
import com.example.adauto.testebatch.entity.dto.PersonResponseDTO;
import com.example.adauto.testebatch.service.PersonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController
{
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<List<PersonResponseDTO>> savePerson(@RequestBody PersonRequestDTO personRequestDTO)
    {
        personService.savePerson(personRequestDTO);
        return new ResponseEntity<List<PersonResponseDTO>>(personService.getAll(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAllPerson()
    {
        return new ResponseEntity<List<PersonResponseDTO>>(personService.getAll(), HttpStatus.OK);
    }
}

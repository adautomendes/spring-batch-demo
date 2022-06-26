package com.example.adauto.testebatch.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.adauto.testebatch.entity.Person;
import com.example.adauto.testebatch.entity.dto.PersonRequestDTO;
import com.example.adauto.testebatch.entity.dto.PersonResponseDTO;
import com.example.adauto.testebatch.repository.PersonRepository;

@Service
public class PersonService
{
    @Autowired
    private PersonRepository personRepository;

    public Long savePerson(PersonRequestDTO personRequestDTO)
    {
        for (int i = 0; i < personRequestDTO.getAmount(); i++)
        {
            personRepository.save(PersonRequestDTO.fromDTO(personRequestDTO));
        }

        return personRequestDTO.getAmount();
    }

    public List<PersonResponseDTO> getAll()
    {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                            .map(PersonResponseDTO::toDTO)
                            .collect(Collectors.toList());
    }
}

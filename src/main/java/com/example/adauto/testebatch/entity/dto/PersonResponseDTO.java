package com.example.adauto.testebatch.entity.dto;

import java.util.UUID;

import com.example.adauto.testebatch.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponseDTO
{
    private Integer id;
    private String name;

    public static PersonResponseDTO toDTO(Person person)
    {
        return PersonResponseDTO.builder()
                                .id(person.getId())
                                .name(person.getName())
                                .build();
    }
}

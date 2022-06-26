package com.example.adauto.testebatch.entity.dto;

import com.example.adauto.testebatch.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonRequestDTO
{
    private Long amount;
    private String name;

    public static Person fromDTO(PersonRequestDTO personRequestDTO)
    {
        return Person.builder()
                     .name(personRequestDTO.getName())
                     .build();
    }
}

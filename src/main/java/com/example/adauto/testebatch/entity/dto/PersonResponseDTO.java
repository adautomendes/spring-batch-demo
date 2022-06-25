//**********************************************************************
// Copyright (c) 2022 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
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

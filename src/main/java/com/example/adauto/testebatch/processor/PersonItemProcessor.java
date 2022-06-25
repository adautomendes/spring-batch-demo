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
package com.example.adauto.testebatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.adauto.testebatch.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person>
{
    @Override
    public Person process(Person person)
    {
        return person;
    }
}

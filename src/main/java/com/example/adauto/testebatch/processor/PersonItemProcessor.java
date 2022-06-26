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

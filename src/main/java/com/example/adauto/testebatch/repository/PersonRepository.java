package com.example.adauto.testebatch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.adauto.testebatch.entity.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String>
{
    Page<Person> findAll(Pageable pageable);
}

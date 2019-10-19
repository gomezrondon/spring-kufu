package com.gomezrondon.springkufu.repository

import com.gomezrondon.springkufu.entities.Person
import org.springframework.data.repository.PagingAndSortingRepository

interface PersonRepository: PagingAndSortingRepository<Person, Long>
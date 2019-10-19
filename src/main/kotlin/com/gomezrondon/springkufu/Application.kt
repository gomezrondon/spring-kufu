package com.gomezrondon.springkufu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@RestController
class PersonController(private val personRepository: PersonRepository) {
	@GetMapping("/person")
	fun readAll() = personRepository.findAll()
	@GetMapping("/person/{id}")
	fun readAll2(@PathVariable id:Long) = personRepository.findById(id)
}


interface PersonRepository: PagingAndSortingRepository<Person, Long>

@Entity
data class Person(@Id val id: Long, val firstName: String, val lastName: String, val birthdate: LocalDate?= LocalDate.MIN)

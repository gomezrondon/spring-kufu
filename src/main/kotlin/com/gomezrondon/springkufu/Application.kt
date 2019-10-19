package com.gomezrondon.springkufu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.HandlerFunction
import org.springframework.web.servlet.function.RequestPredicates.*
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.RouterFunctions.nest
import org.springframework.web.servlet.function.RouterFunctions.route
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
class Application{

	@Bean
	fun routes(handler: PersonHandler): RouterFunction<ServerResponse> {

		return nest(
				path("/person"),
				route(
						GET("/{id}"),
						HandlerFunction { handler.readOne(it) })
						.andRoute(method(HttpMethod.GET),
								HandlerFunction { handler.readAll(it) })
		)
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

@Service
class PersonHandler(private val repository: PersonRepository){
	fun readAll(request:ServerRequest):ServerResponse = ServerResponse.ok().body(repository.findAll())
	fun readOne(request:ServerRequest):ServerResponse = ServerResponse.ok().body(
			repository.findById(request.pathVariable("id").toLong())
	)
}

interface PersonRepository: PagingAndSortingRepository<Person, Long>

@Entity
data class Person(@Id val id: Long, val firstName: String, val lastName: String, val birthdate: LocalDate?= LocalDate.MIN)

package com.gomezrondon.springkufu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.servlet.function.*
import org.springframework.web.servlet.function.RequestPredicates.*
import org.springframework.web.servlet.function.RouterFunctions.nest
import org.springframework.web.servlet.function.RouterFunctions.route
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@SpringBootApplication
class Application{

	@Bean
	fun routesWithKotlinDSL(handler: PersonHandler): RouterFunction<ServerResponse> = router {
		"/person".nest {
			GET("/", handler::readAll)
			GET("/{id}", handler::readOne)
		}

		"/otro".nest {
			GET("/", handler::readAll)
			GET("/{id}", handler::readOne)
		}
	}

	@Bean
	fun routesWithKotlinDSL2(handler: PersonHandler): RouterFunction<ServerResponse> = router {
		"/person2".nest {
			GET("/", handler::readAll)
			GET("/{id}", handler::readOne)
		}

		"/otro2".nest {
			GET("/", handler::readAll)
			GET("/{id}", handler::readOne)
		}
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

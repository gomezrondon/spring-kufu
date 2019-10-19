package com.gomezrondon.springkufu.service

import com.gomezrondon.springkufu.repository.PersonRepository
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

class PersonHandler(private val repository: PersonRepository){
    fun readAll(request: ServerRequest): ServerResponse = ServerResponse.ok().body(repository.findAll())
    fun readOne(request: ServerRequest): ServerResponse = ServerResponse.ok().body(
            repository.findById(request.pathVariable("id").toLong())
    )
}

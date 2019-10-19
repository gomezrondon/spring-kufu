package com.gomezrondon.springkufu

import com.gomezrondon.springkufu.config.config
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args){
		addInitializers(config())
	}
}

package com.gomezrondon.springkufu.config

import com.gomezrondon.springkufu.service.PersonHandler
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.beans
import org.springframework.web.servlet.function.router

fun config(): BeanDefinitionDsl = beans {

    bean {
        val handler = PersonHandler(ref())
        router {
            "/person".nest {
                GET("/", handler::readAll)
                GET("/{id}", handler::readOne)
            }

            "/otro".nest {
                GET("/", handler::readAll)
                GET("/{id}", handler::readOne)
            }
        }
    }

    bean {
        val handler = PersonHandler(ref())
        router {
            "/person2".nest {
                GET("/", handler::readAll)
                GET("/{id}", handler::readOne)
            }

            "/otro2".nest {
                GET("/", handler::readAll)
                GET("/{id}", handler::readOne)
            }

            "/otro3".nest {
                GET("/", handler::readAll)
                GET("/{id}", handler::readOne)
            }
        }
    }
}
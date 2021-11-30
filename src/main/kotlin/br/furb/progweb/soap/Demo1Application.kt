package br.furb.progweb.soap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val NAMESPACE_URI = "http://furb.br/progweb/soap"

@SpringBootApplication
class Demo1Application

fun main(args: Array<String>) {
    runApplication<Demo1Application>(*args)
}

package br.furb.progweb.soap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

const val NAMESPACE_URI = "http://furb.br/progweb/soap"

@SpringBootApplication
class SoapApplication

fun main(args: Array<String>) {
    runApplication<SoapApplication>(*args)
}

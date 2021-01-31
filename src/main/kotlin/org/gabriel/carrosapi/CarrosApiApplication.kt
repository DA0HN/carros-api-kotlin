package org.gabriel.carrosapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarrosApiApplication

fun main(args: Array<String>) {
  runApplication<CarrosApiApplication>(*args)
}

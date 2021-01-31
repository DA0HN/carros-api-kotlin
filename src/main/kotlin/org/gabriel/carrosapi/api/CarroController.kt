package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@RestController
@RequestMapping("api/v1/carros")
class CarroController(val service: CarroService) {

  @GetMapping
  fun findAll(): List<Carro> = service.findAll()

  @GetMapping("/{id}")
  fun findById(@PathVariable("id") id: Long): Optional<Carro> {
    return service.getCarroById(id)
  }

  @GetMapping("/tipo/{tipo}")
  fun findByTipo(@PathVariable("tipo") tipo: String): MutableList<Carro> = service.findByTipo(tipo)

}
package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.web.bind.annotation.*
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

  @PostMapping
  fun save(@RequestBody carro: Carro) = service.save(carro)

  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody carro: Carro) = service.update(id, carro)

}
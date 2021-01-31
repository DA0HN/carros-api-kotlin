package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
  fun findAll(): ResponseEntity<List<Carro>> {
    return ResponseEntity.ok(service.findAll())
  }

  @GetMapping("/{id}")
  fun findById(@PathVariable("id") id: Long): ResponseEntity<Carro> {
    val carro = service.findById(id)

    return carro.map { return@map ResponseEntity.ok(it) }
      .orElse(ResponseEntity.notFound().build())
  }

  @GetMapping("/tipo/{tipo}")
  fun findByTipo(@PathVariable("tipo") tipo: String): ResponseEntity<List<Carro>> {
    val carros = service.findByTipo(tipo)

    return if (carros.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(carros)
  }

  @PostMapping
  fun save(@RequestBody carro: Carro) = service.save(carro)

  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody carro: Carro) = service.update(id, carro)

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long) = service.delete(id)

}
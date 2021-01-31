package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.dto.CarroDTO
import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@RestController
@RequestMapping("api/v1/carros")
class CarroController(val service: CarroService) {

  @GetMapping
  fun findAll(): ResponseEntity<List<CarroDTO>> {
    return ResponseEntity.ok(service.findAll())
  }

  @GetMapping("/{id}")
  fun findById(@PathVariable("id") id: Long): ResponseEntity<CarroDTO> {
    val carro = service.findById(id)

    return carro.map { ResponseEntity.ok(it) }.orElse(ResponseEntity.notFound().build())
  }

  @GetMapping("/tipo/{tipo}")
  fun findByTipo(@PathVariable("tipo") tipo: String): ResponseEntity<List<CarroDTO>> {
    val carros = service.findByTipo(tipo)

    return if (carros.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(carros)
  }

  @PostMapping
  fun save(@RequestBody carro: Carro): ResponseEntity<CarroDTO> {
    return try {
      val carroDTO = service.save(carro)
      val location = uri(carroDTO.id)
      ResponseEntity.created(location).build()
    } catch (ex: Exception) {
      ResponseEntity.badRequest().build()
    }
  }

  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody carro: Carro) = service.update(id, carro)

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long) = service.delete(id)

  private fun uri(id: Long): URI = ServletUriComponentsBuilder
    .fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(id)
    .toUri()


}
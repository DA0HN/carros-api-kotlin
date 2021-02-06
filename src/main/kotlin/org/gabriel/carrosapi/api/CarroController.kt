package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.dto.CarroDTO
import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@RestController
@RequestMapping("api/v1/carros")
class CarroController(val service: CarroService) {

  @Secured("ROLE_USER")
  @GetMapping
  fun findAll(): ResponseEntity<List<CarroDTO>> {
    return ResponseEntity.ok(service.findAll())
  }

  @Secured("ROLE_USER")
  @GetMapping("/{id}")
  fun findById(@PathVariable("id") id: Long): ResponseEntity<CarroDTO> {
    val carro = service.findById(id)
    return ResponseEntity.ok(carro)
  }

  @Secured("ROLE_USER")
  @GetMapping("/tipo/{tipo}")
  fun findByTipo(@PathVariable("tipo") tipo: String): ResponseEntity<List<CarroDTO>> {
    val carros = service.findByTipo(tipo)

    return if (carros.isEmpty()) ResponseEntity.noContent().build() else ResponseEntity.ok(carros)
  }

  @PostMapping
  @Secured("ROLE_ADMIN")
  fun save(
    @RequestBody carro: Carro,
    uriComponentsBuilder: UriComponentsBuilder
  ): ResponseEntity<CarroDTO> {
    val carroDTO = service.save(carro)
    val uri = uriComponentsBuilder.path("api/v1/carros/{id}").buildAndExpand(carroDTO.id).toUri()
    return ResponseEntity.created(uri).body(carroDTO)
  }

  @Secured("ROLE_ADMIN")
  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody carro: Carro): ResponseEntity<CarroDTO> {
    val carroDTO = service.update(id, carro)
    return if (carroDTO != null) ResponseEntity.ok(carroDTO) else ResponseEntity.notFound().build()
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long): ResponseEntity<Any> {
    service.delete(id)
    return ResponseEntity.ok().build()
  }
}
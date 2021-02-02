package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.dto.CarroDTO
import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
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
  fun save(
    @RequestBody carro: Carro,
    uriComponentsBuilder: UriComponentsBuilder
  ): ResponseEntity<CarroDTO> {
    return try {
      val carroDTO = service.save(carro)
      val uri = uriComponentsBuilder.path("api/v1/carros/{id}").buildAndExpand(carroDTO.id).toUri()
      ResponseEntity.created(uri).body(carroDTO)
    } catch (ex: Exception) {
      ResponseEntity.badRequest().build()
    }
  }

  @PutMapping("/{id}")
  fun update(@PathVariable id: Long, @RequestBody carro: Carro): ResponseEntity<CarroDTO> {
    val carroDTO = service.update(id, carro)
    return if (carroDTO != null) ResponseEntity.ok(carroDTO) else ResponseEntity.notFound().build()
  }

  @DeleteMapping("/{id}")
  fun delete(@PathVariable id: Long): ResponseEntity<Any> {
    val isDeleted = service.delete(id)
    return if (isDeleted) ResponseEntity.ok().build() else ResponseEntity.notFound().build()
  }
}
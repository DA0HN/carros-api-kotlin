package org.gabriel.carrosapi.domain.service

import org.gabriel.carrosapi.domain.dto.CarroDTO
import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.repository.CarroRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Service
class CarroService(private val repository: CarroRepository, private val modelMapper: ModelMapper) {
  fun findAll(): List<CarroDTO> = repository.findAll().map(this::buildCarroDTO)

  fun findById(id: Long): Optional<CarroDTO> = repository.findById(id).map {
    buildCarroDTO(it)
  }


  fun findByTipo(tipo: String): List<CarroDTO> =
    repository.findByTipo(tipo).map(this::buildCarroDTO)

  fun save(carro: Carro): CarroDTO {
    if (carro.id != null) throw IllegalStateException("Não foi possível salvar o registro")
    repository.save(carro)
    return buildCarroDTO(carro)
  }

  fun update(id: Long?, carro: Carro): CarroDTO? {
    if (id == null) throw IllegalStateException("Não foi possível atualizar o registro")

    val carroOptional = repository.findById(id)

    return if (carroOptional.isPresent) {
      val target = carroOptional.get()
      target.apply {
        this.id = id
        this.nome = carro.nome
        this.tipo = carro.tipo
      }
      repository.save(target)

      buildCarroDTO(target)
    } else {
      null
    }

  }

  fun delete(id: Long) = repository.deleteById(id)

  private fun buildCarroDTO(carro: Carro): CarroDTO = modelMapper.map(carro, CarroDTO::class.java)

}
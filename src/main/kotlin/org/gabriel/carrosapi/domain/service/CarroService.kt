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

  fun findAll(): List<CarroDTO> = repository.findAll().map {
    modelMapper.map(
      it, CarroDTO::class.java
    )
  }

  fun findById(id: Long): Optional<CarroDTO> = repository.findById(id).map {
    modelMapper.map(
      it,
      CarroDTO::class.java
    )
  }


  fun findByTipo(tipo: String): List<CarroDTO> = repository.findByTipo(tipo).map {
    modelMapper.map(
      it, CarroDTO::class.java
    )
  }

  fun save(carro: Carro): CarroDTO {
    if(carro.id != null) throw IllegalStateException("Não foi possível salvar o registro")
    repository.save(carro)
    return modelMapper.map(carro, CarroDTO::class.java)
  }

  fun update(id: Long?, carro: Carro): CarroDTO {
    if (id == null) throw IllegalStateException("Não foi possível atualizar o registro")

    val target = repository.findById(id).map {
      it.id = id
      it.nome = carro.nome
      it.tipo = carro.tipo

      return@map it
    }.orElseThrow { EntityNotFoundException("O carro não foi encontrado") }

    repository.save(target)

    return modelMapper.map(target, CarroDTO::class.java)
  }

  fun delete(id: Long) {
    if (repository.existsById(id)) {
      repository.deleteById(id)
    } else {
      throw EntityNotFoundException("O carro de id $id não existe")
    }
  }

}
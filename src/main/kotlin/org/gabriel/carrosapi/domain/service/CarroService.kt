package org.gabriel.carrosapi.domain.service

import org.gabriel.carrosapi.domain.model.Carro
import org.gabriel.carrosapi.domain.repository.CarroRepository
import org.springframework.stereotype.Service
import java.lang.IllegalStateException
import java.util.*
import javax.persistence.EntityNotFoundException

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Service
class CarroService(private val repository: CarroRepository) {

  fun findAll(): List<Carro> = repository.findAll()

  fun findById(id: Long): Optional<Carro> = repository.findById(id)

  fun findByTipo(tipo: String): MutableList<Carro> = repository.findByTipo(tipo)

  fun save(carro: Carro): Carro = repository.save(carro)

  fun update(id: Long?, carro: Carro): Carro {
    if (id == null) throw IllegalStateException("Não foi possível atualizar o registro")

    val target = findById(id).map {
      it.id = id
      it.nome = carro.nome
      it.tipo = carro.tipo

      return@map it
    }.orElseThrow { EntityNotFoundException("O carro não foi encontrado") }

    return repository.save(target)
  }

}
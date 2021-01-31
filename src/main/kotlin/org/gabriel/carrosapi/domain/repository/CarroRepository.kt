package org.gabriel.carrosapi.domain.repository

import org.gabriel.carrosapi.domain.model.Carro
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
interface CarroRepository : JpaRepository<Carro, Long> {
  fun findByTipo(tipo: String): MutableList<Carro>

}
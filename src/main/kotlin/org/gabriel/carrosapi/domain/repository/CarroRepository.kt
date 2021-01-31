package org.gabriel.carrosapi.domain.repository

import org.gabriel.carrosapi.domain.model.Carro
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
interface CarroRepository : JpaRepository<Carro, Long>
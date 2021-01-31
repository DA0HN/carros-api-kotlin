package org.gabriel.carrosapi.domain.service

import org.gabriel.carrosapi.domain.repository.CarroRepository
import org.springframework.stereotype.Service

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Service
class CarroService(val repository: CarroRepository){

  fun getCarros() = repository.findAll()


}
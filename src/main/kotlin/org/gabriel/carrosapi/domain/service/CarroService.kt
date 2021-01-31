package org.gabriel.carrosapi.domain.service

import org.gabriel.carrosapi.domain.model.Carro
import org.springframework.stereotype.Service

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Service
class CarroService {

  fun getCarros() = listOf(
    Carro(1L, "fusca"),
    Carro(2L, "ferrari"),
    Carro(3L, "gol"),
    Carro(4L, "mustang"),
  )


}
package org.gabriel.carrosapi.api

import org.gabriel.carrosapi.domain.service.CarroService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@RestController
@RequestMapping("api/v1/carros")
class CarroController(val service: CarroService) {

  @GetMapping
  fun get() = service.getCarros()


}
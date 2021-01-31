package org.gabriel.carrosapi.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@RestController
@RequestMapping("/")
class IndexController {

  @GetMapping
  fun hello() = "API dos Carros"

}
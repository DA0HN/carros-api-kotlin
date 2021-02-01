package org.gabriel.carrosapi.domain.dto

import org.gabriel.carrosapi.config.NoArg

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@NoArg
data class CarroDTO(
  var id: Long,
  var nome: String,
  var tipo: String,
)
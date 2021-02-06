package org.gabriel.carrosapi.domain.dto

import com.fasterxml.jackson.databind.ObjectMapper
import org.gabriel.carrosapi.config.NoArg

/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */
@NoArg
data class UserDTO(
  var login: String,
  var nome: String,
  var email: String,
  var token: String?,
) {
  fun toJson(): String = ObjectMapper().writeValueAsString(this)
}
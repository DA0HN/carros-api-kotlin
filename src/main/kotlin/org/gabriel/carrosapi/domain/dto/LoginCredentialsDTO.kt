package org.gabriel.carrosapi.domain.dto

import org.gabriel.carrosapi.config.NoArg

/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */
@NoArg
data class LoginCredentialsDTO(
  val username: String,
  val password: String,
)
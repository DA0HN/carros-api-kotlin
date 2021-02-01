package org.gabriel.carrosapi.domain.model

import org.gabriel.carrosapi.config.NoArg
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Entity
@NoArg
data class Carro(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long?,
  var nome: String,
  var tipo: String,
)
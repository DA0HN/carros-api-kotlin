package org.gabriel.carrosapi.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Entity
data class Carro(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Long?,
  var nome: String,
  var tipo: String,
)
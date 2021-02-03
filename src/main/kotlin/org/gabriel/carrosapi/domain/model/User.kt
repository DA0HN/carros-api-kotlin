package org.gabriel.carrosapi.domain.model

import org.gabriel.carrosapi.config.NoArg
import javax.persistence.*

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@NoArg
@Entity
@Table(name="\"user\"")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,
  val login: String,
  val senha: String,
  val nome: String,
  val email: String,
)
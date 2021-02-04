package org.gabriel.carrosapi.domain.model

import org.gabriel.carrosapi.config.NoArg
import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Entity
@NoArg
data class Role(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,
  val nome: String,
) : GrantedAuthority {
  override fun getAuthority(): String = this.nome
}
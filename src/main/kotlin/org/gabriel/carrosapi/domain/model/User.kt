package org.gabriel.carrosapi.domain.model

import org.gabriel.carrosapi.config.NoArg
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@NoArg
@Entity
@Table(name = "\"user\"")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,
  val login: String,
  val senha: String,
  val nome: String,
  val email: String,

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_roles",
    joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
  )
  val roles: List<Role>
) : UserDetails {
  override fun getAuthorities(): List<Role> = this.roles
  override fun getPassword(): String = this.senha
  override fun getUsername(): String = this.login
  override fun isAccountNonExpired(): Boolean = true
  override fun isAccountNonLocked(): Boolean = true
  override fun isCredentialsNonExpired(): Boolean = true
  override fun isEnabled(): Boolean = true
}
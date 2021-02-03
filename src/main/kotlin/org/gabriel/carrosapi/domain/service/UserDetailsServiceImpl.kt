package org.gabriel.carrosapi.domain.service

import org.gabriel.carrosapi.domain.repository.UserRepository
import org.springframework.security.core.userdetails.User as UserDetail
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.Throws

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Service("userDetailsService")
class UserDetailsServiceImpl(val repository: UserRepository) : UserDetailsService {
  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(username: String): UserDetails {
    val user = repository
      .findByLogin(username)
      .orElseThrow { UsernameNotFoundException("Usuário não encontrado") }
    return UserDetail
      .withUsername(user.login)
      .password(user.senha)
      .roles("USER")
      .build()
  }
}
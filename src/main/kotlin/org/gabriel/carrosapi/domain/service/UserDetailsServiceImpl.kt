package org.gabriel.carrosapi.domain.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Service("userDetailsService")
class UserDetailsServiceImpl(val encoder: BCryptPasswordEncoder) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    if(username == "user") {
      return User.withUsername(username)
        .password(encoder.encode("user"))
        .roles("USER")
        .build()
    }
    else if(username == "admin") {
      return User.withUsername(username)
        .password(encoder.encode("admin"))
        .roles("USER", "ADMIN")
        .build()
    }
    throw UsernameNotFoundException("Usuário não encontrado")
  }
}
package org.gabriel.carrosapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .httpBasic()
      .and()
      .csrf().disable()
  }

  override fun configure(auth: AuthenticationManagerBuilder) {

    val encoder = BCryptPasswordEncoder()

    auth.inMemoryAuthentication()
      .passwordEncoder(encoder)
        .withUser("user")
        .password(encoder.encode("user"))
        .roles("USER")
      .and()
        .withUser("admin")
        .password(encoder.encode("admin"))
        .roles("ADMIN", "USER")
  }
}
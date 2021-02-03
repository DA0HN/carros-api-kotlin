package org.gabriel.carrosapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      .anyRequest().authenticated()
      .and()
      .httpBasic()
  }

}
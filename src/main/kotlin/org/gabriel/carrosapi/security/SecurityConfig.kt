package org.gabriel.carrosapi.security

import org.gabriel.carrosapi.security.handlers.AccessDeniedHandler
import org.gabriel.carrosapi.security.handlers.UnauthorizedHandler
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig(
  @Qualifier("userDetailsService")
  val userDetailsService: UserDetailsService,
  val encoder: BCryptPasswordEncoder,
  val jwtUtil: JWTUtil,
  val modelMapper: ModelMapper,
  val accessDeniedHandler: AccessDeniedHandler,
  val unauthorizedHandler: UnauthorizedHandler,
) : WebSecurityConfigurerAdapter() {

  private val publicResourcesALL = arrayOf<String>()

  private val publicResourcesGET = arrayOf(
    "/api/v1/login/**",
  )

  override fun configure(http: HttpSecurity) {
    http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, *publicResourcesGET).permitAll()
      .antMatchers(*publicResourcesALL).permitAll()
      .anyRequest().authenticated()
      .and().csrf().disable()
      .addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil, modelMapper))
      .addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
      .exceptionHandling()
      .accessDeniedHandler(accessDeniedHandler)
      .authenticationEntryPoint(unauthorizedHandler)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  }

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(userDetailsService).passwordEncoder(encoder)
  }
}
package org.gabriel.carrosapi.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.StringUtils
import org.gabriel.carrosapi.domain.dto.LoginCredentialsDTO
import org.gabriel.carrosapi.domain.dto.UserDTO
import org.gabriel.carrosapi.domain.model.User
import org.gabriel.carrosapi.exceptions.DefaultErrorMessage
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.ServletException


/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */
class JWTAuthenticationFilter(
  authenticationManager: AuthenticationManager?,
  private val jwtUtil: JWTUtil,
  private val modelMapper: ModelMapper
) :
  UsernamePasswordAuthenticationFilter(authenticationManager) {

  init {
    // Altera a url de login
    setFilterProcessesUrl("/api/v1/login")
  }

  override fun attemptAuthentication(
    request: HttpServletRequest?,
    response: HttpServletResponse?
  ): Authentication {
    val login = ObjectMapper().readValue(request?.inputStream, LoginCredentialsDTO::class.java)

    if (StringUtils.isEmpty(login.username) || StringUtils.isEmpty(login.password))
      throw BadCredentialsException("Invalid username/password")

    val authentication = UsernamePasswordAuthenticationToken(login.username, login.password)
    return authenticationManager.authenticate(authentication)
  }

  override fun successfulAuthentication(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    chain: FilterChain?,
    authentication: Authentication?
  ) {
    val userData = authentication?.principal as User
    val userDTO = modelMapper.map(userData, UserDTO::class.java)
    userDTO.token = jwtUtil.createToken(userData)

    response?.let {
      it.addHeader("Authorization", "Bearer " + userDTO.token)
      write(it, HttpStatus.OK, userDTO.toJson())
    }
  }

  @Throws(IOException::class, ServletException::class)
  override fun unsuccessfulAuthentication(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    error: AuthenticationException?
  ) {
    response?.let {
      write(
        it,
        HttpStatus.UNAUTHORIZED,
        DefaultErrorMessage(
          status = HttpStatus.UNAUTHORIZED.value(),
          error = HttpStatus.UNAUTHORIZED.name,
          message = "Não autorizado",
          path = request?.requestURI ?: "",
        ).toJson()
      )
    }
  }

}
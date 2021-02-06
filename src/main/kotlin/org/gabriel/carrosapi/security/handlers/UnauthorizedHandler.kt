package org.gabriel.carrosapi.security.handlers

import org.gabriel.carrosapi.exceptions.DefaultErrorMessage
import org.gabriel.carrosapi.security.write
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */
@Component
class UnauthorizedHandler : AuthenticationEntryPoint {
  override fun commence(
    request: HttpServletRequest?,
    response: HttpServletResponse?,
    exception: AuthenticationException?
  ) {
    response?.let {
      write(
        it,
        HttpStatus.FORBIDDEN,
        DefaultErrorMessage(
          status = HttpStatus.FORBIDDEN.value(),
          error = HttpStatus.FORBIDDEN.name,
          message = "Acesso negado",
          path = request?.requestURI ?: "",
        ).toJson()
      )
    }
  }
}
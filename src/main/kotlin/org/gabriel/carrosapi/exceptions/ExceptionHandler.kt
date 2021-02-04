package org.gabriel.carrosapi.exceptions

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * @project carros-api-kt
 * @author daohn on 02/02/2021
 */
@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(EmptyResultDataAccessException::class)
  fun errorNotFound(exception: Exception): ResponseEntity<Any> {
    return ResponseEntity.notFound().build()
  }

  @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
  fun errorBadRequest(exception: Exception): ResponseEntity<Any> {
    return ResponseEntity.badRequest().build()
  }

  @ExceptionHandler(AccessDeniedException::class)
  fun errorForbidden(exception: Exception, request: WebRequest): ResponseEntity<Any> {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
      DefaultErrorMessage(
        System.currentTimeMillis(),
        HttpStatus.FORBIDDEN.value(),
        HttpStatus.FORBIDDEN.name,
        "Acesso negado",
        (request as ServletWebRequest).request.requestURI,
      )
    )
  }

  override fun handleHttpRequestMethodNotSupported(
    ex: HttpRequestMethodNotSupportedException,
    headers: HttpHeaders,
    status: HttpStatus,
    request: WebRequest
  ): ResponseEntity<Any> {

    var message = "${ex.method} não é suportado para essa requisição. Os métodos suportados são: "

    ex.supportedMethods?.forEach { method -> message += "$method " }

    return ResponseEntity
      .status(HttpStatus.METHOD_NOT_ALLOWED)
      .body(
        DefaultErrorMessage(
          System.currentTimeMillis(),
          status.value(),
          status.name,
          message,
          (request as ServletWebRequest).request.requestURI,
        )
      )
  }

}
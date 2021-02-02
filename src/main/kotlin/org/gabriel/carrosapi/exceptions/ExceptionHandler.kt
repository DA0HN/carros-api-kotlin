package org.gabriel.carrosapi.exceptions

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

/**
 * @project carros-api-kt
 * @author daohn on 02/02/2021
 */
@RestControllerAdvice
class ExceptionHandler {

  @ExceptionHandler(EmptyResultDataAccessException::class)
  fun errorNotFound(exception: Exception): ResponseEntity<Any> {
    return ResponseEntity.notFound().build()
  }

  @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
  fun errorBadRequest(exception: Exception): ResponseEntity<Any> {
    return ResponseEntity.badRequest().build()
  }
}
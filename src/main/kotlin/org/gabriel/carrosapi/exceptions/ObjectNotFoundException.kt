package org.gabriel.carrosapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

/**
 * @project carros-api-kt
 * @author daohn on 02/02/2021
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ObjectNotFoundException(message: String?, cause: Throwable?) :
  RuntimeException(message, cause) {
  constructor(message: String?) : this(message, null)
}

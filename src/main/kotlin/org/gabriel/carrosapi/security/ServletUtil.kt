package org.gabriel.carrosapi.security

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.io.IOException
import javax.servlet.http.HttpServletResponse


/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */

@Throws(IOException::class)
fun write(response: HttpServletResponse, status: HttpStatus, json: String?) {
  response.status = status.value()
  response.characterEncoding = "UTF-8"
  response.contentType = MediaType.APPLICATION_JSON_VALUE
  json?.let {
    response.writer.write(json)
  }
}
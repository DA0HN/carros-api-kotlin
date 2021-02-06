package org.gabriel.carrosapi.exceptions

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.Serializable

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
data class DefaultErrorMessage(
  val timeStamp: Long = System.currentTimeMillis(), val status: Int, val error: String,
  val message: String, val path: String,
) :
  Serializable {
  private val serialVersionUID = 1L
  fun toJson(): String = ObjectMapper().writeValueAsString(this)
}
package org.gabriel.carrosapi.exceptions

import java.io.Serializable

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
data class DefaultErrorMessage(
  val timeStamp: Long, val status: Int, val error: String,
  val message: String, val path: String,
) :
  Serializable {
    private val serialVersionUID = 1L
  }
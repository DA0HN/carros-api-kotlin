package org.gabriel.carrosapi.domain.repository

import org.gabriel.carrosapi.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * @project carros-api-kt
 * @author daohn on 03/02/2021
 */
interface UserRepository : JpaRepository<User, Long> {
  fun findByLogin(login: String): Optional<User>
}
package org.gabriel.carrosapi.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

/**
 * @project carros-api-kt
 * @author daohn on 05/02/2021
 */
class JWTAuthorizationFilter(
  authenticationManager: AuthenticationManager?,
  private val jwtUtil: JWTUtil,
  private val userDetailsService: UserDetailsService
) :
  BasicAuthenticationFilter(authenticationManager) {

  override fun doFilterInternal(
    request: HttpServletRequest,
    response: HttpServletResponse,
    chain: FilterChain
  ) {

    val headerToken = request.getHeader("Authorization")

    if (headerToken != null && headerToken.startsWith("Bearer ")) {
      val token = headerToken.substring(7)
      val authentication = createAuthentication(token)
      if (authentication != null) {
        SecurityContextHolder.getContext().authentication = authentication
      }
    }
    chain.doFilter(request, response)
  }

  @Throws(UsernameNotFoundException::class)
  private fun createAuthentication(token: String): UsernamePasswordAuthenticationToken? {
    // TODO: testar uma implementação alternativa lançando AccessDeniedException
    if (jwtUtil.isValidToken(token)) {
      val username = jwtUtil.getLogin(token)
      val user = userDetailsService.loadUserByUsername(username)
      return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }
    return null
  }
}
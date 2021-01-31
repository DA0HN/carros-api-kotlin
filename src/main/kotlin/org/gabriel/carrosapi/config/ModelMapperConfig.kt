package org.gabriel.carrosapi.config

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @project carros-api-kt
 * @author daohn on 31/01/2021
 */
@Configuration
class ModelMapperConfig {

  @Bean
  fun modelMapper(): ModelMapper {
    val modelMapper = ModelMapper()
    modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
    return modelMapper
  }

}
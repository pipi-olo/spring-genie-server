package com.pipiolo.springgenie.config

import com.fasterxml.jackson.databind.{PropertyNamingStrategies, PropertyNamingStrategy}
import com.twitter.finatra.jackson.modules.ScalaObjectMapperModule

object AppJacksonModule extends ScalaObjectMapperModule {

  override protected def propertyNamingStrategy: PropertyNamingStrategy = PropertyNamingStrategies.LOWER_CAMEL_CASE
}

package com.pipiolo.springgenie.config

import com.google.inject.{Provides, Singleton}
import com.pipiolo.springgenie.domain.repository.SpringDocRepository
import com.pipiolo.springgenie.infrastructure.SpringDocMemRepository
import com.twitter.inject.TwitterModule

object AppModule extends TwitterModule {

  @Singleton
  @Provides
  def providesSpringDocRepositoryInstance: SpringDocRepository[String] =
    new SpringDocMemRepository()

}

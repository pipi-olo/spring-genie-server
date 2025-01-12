package com.pipiolo.springgenie

import com.google.inject
import com.pipiolo.springgenie.config.{AppJacksonModule, AppModule}
import com.pipiolo.springgenie.presentation.{ExampleController, SpringGenieController}
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter

object SpringGenieServerMain extends SpringGenieServer

class SpringGenieServer extends HttpServer {

  override protected def modules: Seq[inject.Module] = Seq(AppModule)

  override protected def jacksonModule: inject.Module = AppJacksonModule

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[ExampleController]
      .add[SpringGenieController]
  }
}

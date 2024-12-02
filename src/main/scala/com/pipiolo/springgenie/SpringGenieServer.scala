package com.pipiolo.springgenie

import com.pipiolo.springgenie.presentation.ExampleController
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter

object SpringGenieServerMain extends SpringGenieServer

class SpringGenieServer extends HttpServer {

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[ExampleController]
  }
}

package com.pipiolo.springgenie.presentation

import com.pipiolo.springgenie.SpringGenieServer
import com.twitter.finagle.http.Status
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{EmbeddedHttpServer, HttpServer}
import com.twitter.inject.server.{EmbeddedTwitterServer, FeatureTestMixin}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ExampleControllerTest extends AnyFlatSpec with Matchers with FeatureTestMixin {

  override val server = new EmbeddedHttpServer(twitterServer = new HttpServer() {
    override protected def configureHttp(router: HttpRouter): Unit = router.add[ExampleController]
  })

  it should "ping 을 보내면, pong 이 온다." in {
    server.httpGet(path = "/ping", andExpect = Status.Ok, withBody = "pong")
  }
}

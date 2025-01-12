package com.pipiolo.springgenie

import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SpringGenieServerTest extends AnyFlatSpec with Matchers with FeatureTestMixin {

  override val server = new EmbeddedHttpServer(twitterServer = new SpringGenieServer)

  it should "서버가 동작한다." in {
    server.isHealthy shouldBe true
  }

}

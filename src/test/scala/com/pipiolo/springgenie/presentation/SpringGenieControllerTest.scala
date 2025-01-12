package com.pipiolo.springgenie.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.pipiolo.springgenie.SpringGenieServer
import com.pipiolo.springgenie.domain.model.{SpringDocInsertRequest, SpringDocSearchRequest, SpringDocSearchResponse}
import com.twitter.finagle.http.{Method, Request, Status, Version}
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.inject.server.FeatureTestMixin
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class SpringGenieControllerTest extends AnyFlatSpec with Matchers with FeatureTestMixin {

  // map to json
  private val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  override val server = new EmbeddedHttpServer(twitterServer = new SpringGenieServer {
    override protected def configureHttp(router: HttpRouter): Unit = router.add[SpringGenieController]
  })

  it should "서버가 동작한다." in {
    server.isHealthy shouldBe true
  }

  it should "스프링 문서 내용을 HTTP 통신을 통해 저장할 수 있다." in {
    val document = "hello spring world"
    val vector   = Seq(0.1f, 0.2f, 0.3f)

    // insert vector
    val insertRequest = SpringDocInsertRequest(document = document, vector = vector)

    server.httpPost(
      path = "/springdoc",
      headers = Map("Content-Type" -> "application/json"),
      postBody = mapper.writeValueAsString(insertRequest),
      andExpect = Status.Ok
    )

    // search vector
    val searchRequest  = SpringDocSearchRequest(vector = vector)
    val searchResponse = SpringDocSearchResponse(document = document)

    val request = Request(version = Version.Http11, method = Method.Get, uri = "/springdoc")
    request.setContentTypeJson()
    request.setContentString(mapper.writeValueAsString(searchRequest))

    server.httpRequest(request = request, andExpect = Status.Ok, withJsonBody = mapper.writeValueAsString(searchResponse))
  }
}

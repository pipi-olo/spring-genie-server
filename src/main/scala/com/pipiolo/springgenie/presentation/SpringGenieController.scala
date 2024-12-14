package com.pipiolo.springgenie.presentation

import com.google.inject.Inject
import com.pipiolo.springgenie.application.SpringGenieService
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

import scala.util.Random

class SpringGenieController @Inject() (springGenieService: SpringGenieService[String]) extends Controller {

  get("/springdoc") { request: Request =>
    springGenieService.search(Seq.fill(5)(Random.nextFloat))
  }

  post("/springdoc") { request: Request =>
    springGenieService.insert("asd", Seq.fill(5)(Random.nextFloat))
  }
}

package com.pipiolo.springgenie.presentation

import com.google.inject.Inject
import com.pipiolo.springgenie.application.SpringGenieService
import com.pipiolo.springgenie.domain.model.{SpringDocInsertRequest, SpringDocSearchRequest}
import com.twitter.finagle.http.{Response, Status}
import com.twitter.finatra.http.Controller

class SpringGenieController @Inject() (springGenieService: SpringGenieService) extends Controller {

  get("/springdoc") { searchRequest: SpringDocSearchRequest =>
    springGenieService.search(searchRequest.vector)
  }

  post("/springdoc") { insertRequest: SpringDocInsertRequest =>
    springGenieService.insert(insertRequest.document, insertRequest.vector)

    Response(status = Status.Ok)
  }
}

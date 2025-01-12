package com.pipiolo.springgenie.application

import com.google.inject.Inject
import com.pipiolo.springgenie.domain.model.SpringDocSearchResponse
import com.pipiolo.springgenie.domain.repository.SpringDocRepository

class SpringGenieService @Inject() (springDocRepository: SpringDocRepository) {

  def insert(document: String, vector: Seq[Float]): Unit = {
    springDocRepository.insert(document, vector)
  }

  def search(vector: Seq[Float]): SpringDocSearchResponse = {
    val doc = springDocRepository.search(vector)
    SpringDocSearchResponse(document = doc)
  }
}

package com.pipiolo.springgenie.application

import com.google.inject.Inject
import com.pipiolo.springgenie.domain.repository.SpringDocRepository

class SpringGenieService[K] @Inject() (springDocRepository: SpringDocRepository[K]) {

  def insert(doc: K, vector: Seq[Float]): Unit = {
    springDocRepository.insert(doc, vector)
  }

  def search(vector: Seq[Float]): K = {
    springDocRepository.search(vector)
  }
}

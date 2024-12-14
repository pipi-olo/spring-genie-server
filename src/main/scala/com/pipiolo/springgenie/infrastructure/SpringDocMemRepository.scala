package com.pipiolo.springgenie.infrastructure

import com.pipiolo.springgenie.domain.repository.SpringDocRepository

import scala.collection.mutable

class SpringDocMemRepository extends SpringDocRepository[String] {

  private val vectorMap: mutable.Map[String, Seq[Float]] = mutable.Map.empty

  override def insert(doc: String, vector: Seq[Float]): Unit = {
    vectorMap += (doc -> vector)
  }

  override def search(query: Seq[Float]): String = {
    vectorMap
      .map { case (doc, vector) =>
        doc -> cosineSimilarity(query, vector)
      }
      .maxBy(_._2)
      ._1
  }


  override def close(): Unit = {
    vectorMap.clear()
  }
}

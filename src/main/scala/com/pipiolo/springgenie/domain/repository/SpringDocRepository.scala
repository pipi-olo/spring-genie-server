package com.pipiolo.springgenie.domain.repository

trait SpringDocRepository {

  def insert(document: String, vector: Seq[Float]): Unit

  def search(vector: Seq[Float]): String

  def close(): Unit

  // 코사인 유사도 계산 함수
  protected def cosineSimilarity(v1: Seq[Float], v2: Seq[Float]): Float = {
    val dotProduct = v1.zip(v2).map { case (x, y) => x * y }.sum
    val magnitude1 = math.sqrt(v1.map(x => x * x).sum).toFloat
    val magnitude2 = math.sqrt(v2.map(x => x * x).sum).toFloat
    dotProduct / (magnitude1 * magnitude2)
  }
}

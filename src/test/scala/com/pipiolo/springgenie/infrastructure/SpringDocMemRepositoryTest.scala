package com.pipiolo.springgenie.infrastructure

import org.scalatest.BeforeAndAfterEach
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.UUID
import scala.util.Random

class SpringDocMemRepositoryTest extends AnyFlatSpec with Matchers with BeforeAndAfterEach {

  private val springDocMemRepository = new SpringDocMemRepository()

  override protected def afterEach(): Unit = {
    springDocMemRepository.close()
  }

  it should "벡터를 저장 및 조회할 수 있다." in {
    val key    = UUID.randomUUID().toString
    val vector = Seq(0.1f, 0.2f, 0.3f, 0.4f, 0.5f)

    springDocMemRepository.insert(key, vector)

    springDocMemRepository.search(vector) shouldBe key
  }

  it should "여러 개의 벡터 중 가장 가까운 벡터를 조회할 수 있다." in {
    val key    = UUID.randomUUID().toString
    val vector = Seq(0.1f, 0.2f, 0.3f, 0.4f, 0.5f)

    springDocMemRepository.insert(key, vector)

    (0 to 100).foreach { _ =>
      springDocMemRepository.insert(UUID.randomUUID().toString, Seq.fill(5)(Random.nextFloat))
    }

    springDocMemRepository.search(vector) shouldBe key
  }

}

package com.salutr.io.czech
import org.junit.Test

class SingleWordNameExtractorTest {

  @Test
  def testMapPhase {
    val compoundNameCounts = List(("foo", 1), ("bar baz", 2), ("bar foo", 3))
    val extractor = new SingleWordNameExtractor()
    val singleNameCounts = extractor.extract(compoundNameCounts.iterator)
    for (item <- singleNameCounts)
      println(item._1 + " " + item._2)
  }
}
package com.salutr.declinator
import org.junit.Test
import org.junit.Assert._

class CzechVocativeDeclinatorTest {

  val declinator = new CzechVocativeDeclinator()

  @Test
  def testDeclineSingleWord {
    println(declinator.declineWord("Bohum’r", null))
  }
}
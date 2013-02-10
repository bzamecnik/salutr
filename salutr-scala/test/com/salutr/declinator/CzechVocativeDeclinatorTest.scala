package com.salutr.declinator
import org.junit.Test
import org.junit.Assert._
import java.nio.charset.Charset
import java.io.InputStream
import java.io.InputStreamReader

class CzechVocativeDeclinatorTest {

  val declinator = new CzechVocativeDeclinator()

  @Test
  def testDeclineSingleWord {
    val nominative = "Bohumír"
    println(nominative)
    println(declinator.declineWord(nominative, null))
  }
}
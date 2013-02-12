package com.salutr
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import com.salutr.declinator.CzechVocativeDeclinator

object VocativeDeclinatorApp {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("arguments: INPUT_FILE OUTPUT_FILE")
      sys.exit()
    }
    val inputFileName = args(0)
    val input = Source.fromFile(new File(inputFileName), "UTF-8")
    val outputFileName = args(1)
    val writer = new PrintWriter(new File(outputFileName), "UTF-8")

    val startTime = System.nanoTime()
    
    val declinator = new CzechVocativeDeclinator()
    for {
      line <- input.getLines()
      val columns = line.split(",")
      if columns.length > 0
      val nominative = toTitleCase(columns(0))
    } {
      try {
        val result = declinator.declineWord(nominative, null)
        val vocative = result.get("vocative")
        if (vocative.isDefined) {
          writer.println(nominative + "," + toTitleCase(vocative.get))
        } else {
          System.err.println(nominative + ": " + result.get("message"))
        }
      } catch {
        case ex: Exception => System.err.println(nominative + ": " + ex)
      }
    }
    writer.flush
    
    val endTime = System.nanoTime()
    
    printf("Total time: %d ms\n", (endTime - startTime) / 1000000)
  }

  def toTitleCase(text: String) = {
    text.take(1).toUpperCase + text.drop(1).toLowerCase
  }
}
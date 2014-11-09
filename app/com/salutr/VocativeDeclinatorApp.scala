package com.salutr
import scala.io.Source
import java.io.PrintWriter
import java.io.File
import com.salutr.declinator.CzechVocativeDeclinator
import com.salutr.declinator.DeclinationService

object VocativeDeclinatorApp {
  
  val separator = ";"
  
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
    
    val declinator = new DeclinationService()
    for {
      line <- input.getLines()
      
        columns = line.split(separator)
      if columns.length > 0
      val nominative = columns(columns.length - 1)
    } {
      try {
        val vocative = declinator.declineCompoundName(nominative)
        writer.println(line + separator + vocative)
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
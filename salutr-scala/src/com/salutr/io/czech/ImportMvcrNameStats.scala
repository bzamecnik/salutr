package com.salutr.io.czech
import java.io.File
import java.io.PrintWriter

import scala.io.Source

class ImportMvcrNameStats {
  def importCsv(input: Source) = {
    val inputLines = input.getLines()
    val firstLine = inputLines.next()
    for {
      line <- inputLines
        .takeWhile(!_.startsWith("-"))
        .filterNot(_.matches(".*[?ÿ].*"))
      val columns = line.split(";")
      val name = fixEncoding(columns(0).trim)
      if !name.isEmpty()
    } yield {
      val count = columns(columns.length - 1).toInt
      (name, count)
    }
  }

  private def fixEncoding(name: String) = {
    name
      .replace('\u00ac', '\u017d') // NOT SIGN -> LATIN CAPITAL LETTER Z WITH CARON
      .replace('\u0104', '\u013d') // LATIN CAPITAL LETTER A WITH OGONEK -> LATIN CAPITAL LETTER L WITH CARON 
      .replace('\u0105', '\u013e') // LATIN SMALL LETTER A WITH OGONEK -> LATIN SMALL LETTER L WITH CARON
  }

  def printCsv(lines: Iterator[(String, Any)], writer: PrintWriter) = {
    writer.println(""""name","count"""")
    for (line <- lines)
      writer.println("\"" + line._1 + "\",\"" + line._2 + "\"")
    writer.flush()
  }
}

object ImportMvcrNameStats {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("arguments: INPUT_FILE OUTPUT_FILE")
      sys.exit()
    }
    val inputFileName = args(0)
    val input = Source.fromFile(new File(inputFileName), "Windows-1250")
    val outputFileName = args(1)
    val writer = new PrintWriter(new File(outputFileName), "UTF-8")

    val importer = new ImportMvcrNameStats()
    def compoundNameCounts = importer.importCsv(input)

    val extractor = new SingleWordNameExtractor()
    val singleNameCounts = extractor.extract(compoundNameCounts)
    importer.printCsv(singleNameCounts, writer)
  }
}
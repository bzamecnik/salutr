package com.salutr.io.czech
import java.io.PrintWriter

import scala.io.Source

import org.junit.Ignore
import org.junit.Test

/**
 * Imports given and family names of Czech citizens and their frequencies from
 * the Ministy of the Interior.
 *
 * @see http://www.mvcr.cz/clanek/cetnost-jmen-a-prijmeni-722752.aspx
 */
class ImportMvcrNameStatsTest {
  @Test
  def convertSomeNames = {
    val inputString = """jmeno/kraj;0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;;SUMA
A-MI;0;0;0;0;0;0;0;0;0;1;;1
A-RIA;0;0;0;0;0;0;0;0;0;1;;1
AAGOT;0;0;0;0;0;0;0;0;0;1;;1
A?KAR;0;0;0;0;0;0;0;0;0;0;1;;1
BˇCZKOVÁ;0;0;0;0;0;1;;1
  ŘÍNA  ;0;0;0;0;0;0;0;0;0;0;1;;1
ÜLKEM;0;0;0;0;0;0;0;0;0;0;0;0;1;;1
 ? ;0;0;0;0;0;0;0;0;0;0;0;0;1;;1
¬Ąą;0;0;0;0;0;0;0;0;0;0;0;0;1;;1
---
SUMA;628578;1160861;292263;545102;430954;1229542;636042;508060;553772;1247384;817353;506106;587105;1113429;;10256551
"""
    val input = Source.fromString(inputString)
    val importer = new ImportMvcrNameStats()
    val convertedLines = importer.importCsv(input)
    importer.printCsv(convertedLines, new PrintWriter(System.out))
  }

  @Test
  //@Ignore
  def printUtf8Literal {
    for (char <- "¬ŽĄĽąľ") printf("%c \\u%04x\n", char, char.toInt)
  }
}
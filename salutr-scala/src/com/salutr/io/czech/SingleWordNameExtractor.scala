package com.salutr.io.czech

import scala.Array.canBuildFrom
import scala.collection.TraversableOnce.flattenTraversableOnce

class SingleWordNameExtractor {
  def extract(names: Iterator[(String, String)]) = {

    def splitCompoundName(name: String) = {
      name.replaceAll("[, /()-]+", " ").replaceAll("\\.", ". ").split(" ");
    }

    val singleNameCounts = names.map({ pair =>
      val compoundName = pair._1
      val count = pair._2.toInt
      splitCompoundName(compoundName).map((_, count))
    }).flatten.toList

    singleNameCounts.groupBy(_._1).mapValues({ pairs =>
      pairs.map(_._2).foldLeft(0)(_ + _)
    }).iterator
  }
}
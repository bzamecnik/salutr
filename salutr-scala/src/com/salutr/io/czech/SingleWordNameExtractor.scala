package com.salutr.io.czech

import scala.Array.canBuildFrom
import scala.collection.TraversableOnce.flattenTraversableOnce

class SingleWordNameExtractor {
  /**
   * Extract single-word names from multi-word ones
   * and computes total counts of each single-word name.
   * 
   * @names sequence of (name, count)
   * @return sequence of (name, count)
   */
  def extract(names: Iterator[(String, String)]) = {

    def splitCompoundName(name: String) = {
      name.replaceAll("[, /()-]+", " ").replaceAll("\\.", ". ").split(" ");
    }

    val singleNameCounts = for {
      (compoundName, count) <- names
      simpleName <- splitCompoundName(compoundName)
      if simpleName.length > 1
    } yield (simpleName, count.toInt)

    (for {
      (name, items) <- singleNameCounts.toList.groupBy(_._1)
      totalCount = items.map(_._2).sum
    } yield (name, totalCount)).iterator
  }
}
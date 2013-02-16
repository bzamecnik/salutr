package com.salutr.declinator


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


class DeclinationService {

	val declinator = new CzechVocativeDeclinator()
  
	def declineToVocative(name: String ) = declineCompoundName(name)

	/**
	 * Declines a name which might be composed of several names divided by
	 * spaces, hyphens, or commas.
	 * 
	 * Eg. "Marie Terezie", "Jean-Paul", "A.Foo, PhD." etc.
	 */
	def declineCompoundName(compoundName: String): String = {
		val declinedWords = new ArrayList[String]()
		for (word <- compoundName.split(" ")) {
			val parts = word.split("-")
			val declinedParts = new ArrayList[String]()
			for (part <- parts) {
				var suffix: String = null
				var namePart = part
				if (part.endsWith(",")) {
					namePart = part.substring(0, part.length() - 1)
					suffix = ","
				}
				var vocative: String  = declineSingleWordName(part)
				if (suffix != null) {
					vocative += suffix
				}
				declinedParts.add(vocative)
			}
			val declinedWord: String = StringUtils.join(declinedParts, "-")
			declinedWords.add(declinedWord)
		}
		val vocative: String = StringUtils.join(declinedWords, " ")
		return vocative
	}

	/**
	 * Declines a single-word name.
	 */
	def declineSingleWordName(singleName: String): String = {
		if (singleName.isEmpty() || (singleName.length <= 2) || singleName.endsWith(".")) {
			// do not decline too short parts (probably preposistions like de, le)
			// or abbreviations and titles, eg. J.F. Kennedy., Jr., MUDr.
			return singleName;
		}

		val result = declinator.declineWord(singleName)
		result.getOrElse("vocative", singleName)
	}




}

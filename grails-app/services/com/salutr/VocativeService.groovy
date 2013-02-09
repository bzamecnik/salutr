package com.salutr

class VocativeService {

	def mongo
	
	String wholeNameToVocative(String names) {
		List<String> words = names.split(" ")
		List<String> declinedWords = []
		for (def word in words) {
			List<String> parts = word.split("-")
			List<String> declinedParts = []
			for (def part in parts) {
				def suffix = ""
				if (part.matches(/.+,$/)) {
					part = part[0..-2]
					suffix = ","
				}
				def vocative = declineSingleWord(part)
				vocative += suffix
				declinedParts.add(vocative)
			}
			def declinedWord = declinedParts.join("-")
			declinedWords.add(declinedWord)
		}
		return declinedWords.join(" ")
	}

	private declineSingleWord(String part) {
		def vocative = part
		if (part.length() > 2 && !part.endsWith(".")) {
			// Do not decline too short parts (probably preposistions like de, le)
			// or abbreviations and titles, eg. J.F. Kennedy., Jr., MUDr.
			def possibleVocative = singleNameToVocative(part)
			if (!possibleVocative) {
				possibleVocative = singleSurnameToVocative(part)
			}
			if (possibleVocative) {
				vocative = possibleVocative
			}
		}
		return vocative
	}
	
    String singleNameToVocative(String name) {
		def db = mongo.getDB("salutr")
		def doc = db.single_names.findOne([_id: name.toUpperCase()])
		return doc?.vocative
    }
	
	String singleSurnameToVocative(String name) {
		def db = mongo.getDB("salutr")
		def doc = db.single_surnames.findOne([_id: name.toUpperCase()])
		return doc?.vocative
	}
}

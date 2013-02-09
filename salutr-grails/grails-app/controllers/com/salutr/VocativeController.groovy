package com.salutr

class VocativeController {

	def vocativeService

	def index() {
		def nominative = params.nominative
		if (!nominative) {
			return
		}
		def vocative = vocativeService.wholeNameToVocative(nominative)
		if (!vocative) {
			flash.message = "Neumíme vyskloňovat."
			vocative = nominative
		}
		[ nominative: nominative, vocative: vocative ]
	}
}

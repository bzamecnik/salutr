db.single_names.find({
	pattern : {
		$exists : 1
	}
}).forEach(function(name) {
	var gender = null;
	if (name.pattern[0] == "m") {
		gender = "M"
	} else if (name.pattern[0] == "ž") {
		gender = "F"
	}
	db.single_names_export.save({
		nominative : name._id,
		vocative : name.vocative,
		gender : gender,
		count : name.value,
		type : "first"
	});
});

db.single_surnames.find({
	// filter out only names with more occurences
	value : {
		$gt : 1
	},
	pattern : {
		$exists : 1
	},
	problem : {
		$exists : 0
	}
}).forEach(function(name) {
	var gender = null;
	if (name.pattern[0] == "ž") {
		gender = "F"
	} else {
		gender = "F"
	}
	db.single_names_export.save({
		nominative : name._id,
		vocative : name.vocative,
		gender : gender,
		count : name.value,
		type : "last"
	});
});

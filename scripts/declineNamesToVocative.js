db.single_names.find().limit(10).forEach(function(doc) {
	var name = doc._id.toLowerCase();
	print(name + " -> " + declineWord(name).vocative);
});

//benchmark(function(collection) {
	db.single_surnames.find({}).forEach(function(doc) {
		var name = doc._id.toLowerCase();
		var result = declineWord(name);
		var vocative = result.vocative;
		if (vocative) {
			vocative = vocative[0].toUpperCase() + vocative.substr(1);
		}
		var attrsToSet = {
			vocative : vocative,
		};
		if (result.pattern) {
			attrsToSet.pattern = result.pattern;
		}
		if (result.message) {
			attrsToSet.problem = result.message;
		}
		db.single_surnames.update({
			'_id' : doc._id
		}, {
			$set : attrsToSet
		});
	});
//});

function toTitleCase(str) {
	return str.replace(/\w\S*/g, function(txt) {
		return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
	});
}
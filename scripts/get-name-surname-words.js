name_map = function() {
	emit(this._id, {
		name : true,
		surname : false,
		count : 0
	});
}
surname_map = function() {
	emit(this._id, {
		name : false,
		surname : true,
		count : this.value
	});
}

reduce = function(key, values) {
	// name|surname is set to true if any value's respective indicator is true

	var result = {
		name : false,
		surname : false,
		/* just surname count */
		count : 0
	};

	for (index in values) {
		var item = values[index];
		result.name = result.name || item.name;
		result.surname = result.surname || item.surname;
		result.count += item.count;
	}

	return result;
}

// NOTES:
// - count > 1 to filter out outliers (eg. erroneous data)

db.runCommand({
	"mapreduce" : "single_names",
	"map" : name_map,
	"reduce" : reduce,
	"query" : {
		"value" : {
			"$gt" : 1
		}
	},
	"out" : {
		reduce : "ambiguous_names"
	}
});

db.runCommand({
	"mapreduce" : "single_surnames",
	"map" : surname_map,
	"reduce" : reduce,
	"query" : {
		"value" : {
			"$gt" : 1
		}
	},
	"out" : {
		reduce : "ambiguous_names"
	}
});

// ambiguous names:
db.ambiguous_names.find({
	'value.name' : true,
	'value.surname' : true
}).sort({
	'value.count' : -1
})

map = function() {
	var words = this.name.replace(/[, \/()-]+/g," ").replace(/\./g, ". ").split(" ");

	for ( var index in words) {
		var word = words[index];
		if (word) {
			emit(word, this.count);
		}
	}
}
reduce = function(key, values) {
	var count = 0;

	for (index in values) {
		if (values[index]) {
			count += values[index];
		}
	}

	return count;
}

db.runCommand({
	"mapreduce" : "names",
	"map" : map,
	"reduce" : reduce,
	"out" : "single_names"
});

db.runCommand({
	"mapreduce" : "surnames",
	"map" : map,
	"reduce" : reduce,
	"out" : "single_surnames"
});

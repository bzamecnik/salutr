db.name_vocatives.aggregate([ {
	$group : {
		_id : "$nominativeAscii",
		count : {
			$sum : 1
		}
	}
}, {
	$match : {
		count : {
			$gt : 1
		}
	}
} ]);